package com.efimchick.ifmo.io.filetree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FileTreeImpl implements FileTree {
    private static final String STRAIGHT_PIPE = "│  ";
    private static final String MIDDLE_PIPE = "├─ ";
    private static final String LEAF_PIPE = "└─ ";
    private static final String OUTPUT_FORMAT = "%s %d bytes\n";
    private static final Comparator<Path> PATH_COMPARATOR = (path1, path2) -> {
        String name1 = path1.getFileName().toString().toLowerCase();
        String name2 = path2.getFileName().toString().toLowerCase();
        return name1.compareTo(name2);
    };


    public Optional<String> tree(Path inputPath) {
        try {
            //null input case or non-existent file case
            if (inputPath == null || !Files.exists(inputPath)) {
                return Optional.empty();
            }

            //single file case
            if (Files.isRegularFile(inputPath)) {
                String output = formatOutput(inputPath);
                return Optional.of(output);
            }

            String root = formatOutput(inputPath);


            String hierarchyTree =
                    constructTree(inputPath, 0); 

            return Optional.of(root + hierarchyTree);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private String constructTree(Path inputPath, int depth) throws IOException {

        List<Path> directories = getDirs(inputPath);
        List<Path> files = getFiles(inputPath);

        //create representation of directory hierarchy
        StringBuilder hierarchy = new StringBuilder();

        //add directories
        for (Path directory : directories) {
            boolean isLast = isLastElement(directory, directories);

            hierarchy
                    //.append((getStraightPipes(depth)))
                    .append((getStraightPipes(depth, directory)))
                    .append(isLast && (files.isEmpty())
                            ? LEAF_PIPE : MIDDLE_PIPE)
                    .append(formatOutput(directory))
                    .append(constructTree(directory, (depth + 1)));
        }


        //add files
        for (Path file : files) {
            hierarchy
                    .append(getStraightPipes(depth, file))
                    //.append(isLastDirectory && isPreviousFilesEmpty ? getIndentedStraightPipes(depth) : getStraightPipes(depth))
                    .append(isLastElement(file, files)
                            ? LEAF_PIPE : MIDDLE_PIPE)
                    .append(formatOutput(file));
        }

        return hierarchy.toString();
    }

    private List<Path> getFiles(Path inputPath) {
        try (Stream<Path> stream = Files.list(inputPath)) {
            return stream.filter(Files::isRegularFile).sorted(PATH_COMPARATOR).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private List<Path> getDirs(Path inputPath) {
        try (Stream<Path> stream = Files.list(inputPath)) {
            return stream.filter(Files::isDirectory).sorted(PATH_COMPARATOR).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private Long getSize(Path path) throws IOException {
        //returns size of input path in bytes
        if (Files.isRegularFile(path)) {
            //single file case
            return Files.size(path);
        } else if (Files.isDirectory(path)) {
            //stream directory via Files.walk
            try (Stream<Path> pathStream = Files.walk(path)) {
                return pathStream
                        .filter(Files::isRegularFile)
                        .mapToLong(currentPath -> {
                            try {
                                return Files.size(currentPath);
                            } catch (IOException e) {
                                e.printStackTrace();
                                return 0;
                            }
                        })
                        .sum();
            }
        }
        return 0L;
    }

    private String formatOutput(Path path) throws IOException {
        return String.format(OUTPUT_FORMAT, path.getFileName().toString(), getSize(path));
    }

    private Boolean isLastElement(Path path, List<Path> lst) {
        return lst.indexOf(path) == (lst.size() - 1);
    }


    private String getStraightPipes(int depth, Path inputPath) {
        List<String> pipes = new ArrayList<>();

        inputPath = inputPath.getParent();

        for (int i = 0; i < depth; i++) {
            //pipes.add(0, "   ");
            if (!isLastElement(inputPath, getDirs(inputPath.getParent())) || !getFiles(inputPath.getParent()).isEmpty() ) {
                pipes.add(0, STRAIGHT_PIPE);
            }
            else {
                pipes.add(0, "   ");
            }
            inputPath = inputPath.getParent();
        }

        return String.join("", pipes);
    }






}
