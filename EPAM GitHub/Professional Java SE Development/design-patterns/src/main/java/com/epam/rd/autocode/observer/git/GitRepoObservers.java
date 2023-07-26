package com.epam.rd.autocode.observer.git;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GitRepoObservers {
    public static Repository newRepository(){
        return new Repository() {
            List<WebHook> webHooks = new ArrayList<>();

            @Override
            public void addWebHook(WebHook webHook) {
                webHooks.add(webHook);
            }

            @Override
            public Commit commit(String branch, String author, String[] changes) {
                Commit commit = new Commit(author, changes);
                Event event = new Event(Event.Type.COMMIT, branch, List.of(commit));

                for (WebHook webHook : webHooks) {
                    if (webHook.branch().equals(branch) && webHook.type() == Event.Type.COMMIT) {
                        webHook.onEvent(event);
                    }
                }

                return commit;
            }

            @Override
            public void merge(String sourceBranch, String targetBranch) {
                List<Commit> mergedCommits = new ArrayList<>();

                for (WebHook webHook : webHooks) {
                    if (webHook.branch().equals(targetBranch) && webHook.type() == Event.Type.MERGE) {
                        // Collect commits from the source branch and add them to the mergedCommits list
                        for (WebHook commitWebHook : webHooks) {
                            if (commitWebHook.branch().equals(sourceBranch) && commitWebHook.type() == Event.Type.COMMIT) {
                                List<Event> commitEvents = commitWebHook.caughtEvents();
                                for (Event event : commitEvents) {
                                    List<Commit> commits = event.commits();
                                    mergedCommits.addAll(commits);
                                }
                            }
                        }

                        Event event = new Event(Event.Type.MERGE, targetBranch, mergedCommits);
                        webHook.onEvent(event);
                    }
                }
            }

        };

    }

    public static WebHook mergeToBranchWebHook(String branchName){
        return new WebHook() {
            private List<Event> caughtEvents = new ArrayList<>();

            @Override
            public String branch() {
                return branchName;
            }

            @Override
            public Event.Type type() {
                return Event.Type.MERGE;
            }

            @Override
            public List<Event> caughtEvents() {
                //remove duplicates
                return (caughtEvents).stream().distinct().collect(Collectors.toList());
            }

            @Override
            public void onEvent(Event event) {
                caughtEvents.add(event);
            }
        };
    }

    public static WebHook commitToBranchWebHook(String branchName){
        return new WebHook() {

            private List<Event> caughtEvents = new ArrayList<>();
            @Override
            public String branch() {
                return branchName;
            }

            @Override
            public Event.Type type() {
                return Event.Type.COMMIT;
            }

            @Override
            public List<Event> caughtEvents() {
                //remove duplicates
                return (caughtEvents).stream().distinct().collect(Collectors.toList());
            }

            @Override
            public void onEvent(Event event) {
                caughtEvents.add(event);
            }
        };
    }


}
