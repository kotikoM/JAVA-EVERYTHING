package pgdp;


import java.time.temporal.IsoFields;
import java.util.Arrays;
import java.util.Random;


public class Album {
    private String title;
    private int releaseYear;
    private Song[] songs;


    public Album(String title, int releaseYear, Song[] songs) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.songs = songs;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public Song[] getSongs() {
        return songs;
    }

    public int getSongsLength(){return songs.length;}




    private Song[] filter(Song[] songs){
        //filter is used to remove duplicates from the given array first before adding another
        //assuming that there are no nulls
        int a =0;
        int c=0;
        int d=0;
        Song[] duplicate = songs.clone();
        Song[] firstFilter=new Song[songs.length];
        Song temp=new Song("1nji%&1o23", 4624621, 654, 457574);
        //random song to replace duplicates in to not insert another duplicates in the first-filter array

        for (Song song : songs) {
            a = 0;
            for (int j = 0; j < songs.length; j++) {
                if (duplicate[j].isEqual(song)) {
                    d = j;
                    duplicate[j] = temp;
                    a++;
                }
            }
            if (a == 1) { //if there is only one song we just put it in the firstfilter array
                firstFilter[c] = song;
                c++;
            } else if (a > 1) { //if song repeats multiple times we put it in the once
                firstFilter[c] = songs[d];
                c++;
            }
        }


        Song[] filter=new Song[c];
        for (int i = 0; i <c ; i++) {
            filter[i]=firstFilter[i]; //removing nulls
        }



        return filter;
    }


    public int addSongs(Song[] songs) {

        int a=0;
        for (int i = 0; i < this.songs.length ; i++) {
            if (this.songs[i] == null) {
                a++;
            }
        }
        if (a==this.songs.length){
            songs=filter(songs);
            this.songs=songs;
        }else
            songs = filter(songs);

            int NumAddedSongs = songs.length;
            int b = songs.length;
            a = 0;
            Song[] filteredSongs = new Song[songs.length];

            //similar filtering mechanism as in filter, but slitly different
            for (Song song : songs) {
                for (Song value : this.songs) {
                    if (value.isEqual(song))
                        NumAddedSongs--;
                }
                if (NumAddedSongs == b) {
                    filteredSongs[a] = song;
                    a++;
                }
                b = NumAddedSongs;
            }


            //inserting filtered songs+this.songs in this.songs
            Song[] SongsFinal = new Song[this.songs.length + NumAddedSongs];
            for (int i = 0; i < this.songs.length; i++) {
                SongsFinal[i] = this.songs[i];
            }
            for (int j = 0; j < NumAddedSongs; j++) {
                SongsFinal[this.songs.length + j] = filteredSongs[j];
            }

            this.songs = SongsFinal;



        return NumAddedSongs;
    }



    public Song[] shuffle() {
        Song[] shuffled = new Song[songs.length];
        Random rand = new Random();
        for (int i = 0; i < shuffled.length; i++) {
            shuffled[i] = this.songs[rand.nextInt(0, songs.length)]; //selecting random posiion from songs and creating shuffled one
        }
        return shuffled;
    }


    public Song[] sortByTitle(boolean isAscending) {
        Song[] sorted = this.songs.clone();
        for (int i = 0; i < this.songs.length - 1; i++) {  //A-Z, idk how it works but it works
            for (int j = 0; j < this.songs.length; j++) {
                if (this.songs[i].getTitle().compareTo(this.songs[j].getTitle()) >= 0) {
                    Song temp = sorted[i];
                    sorted[i] = sorted[j];
                    sorted[j] = temp;
                }
            }
        }
        //first we sort in ascending order and if isAscending is flase then we just switch array places from i to length-i
        if (isAscending) return sorted;
        else {Song[] sortedDescending = new Song[sorted.length];
            for (int i = songs.length - 1; i >= 0; i--) {
            sortedDescending[songs.length - 1 - i] = sorted[i];
        }
            return sortedDescending;
        }
    }

    public Song[] sortByDuration(boolean isAscending) {
        //similar to sortByTitle
        int[] durations=new int[this.songs.length];
        Song[] SortedDuration = new Song[this.songs.length];
        int d=0;

        for (int i = 0; i < this.songs.length; i++) {
            durations[i]=this.songs[i].getDuration();
        }
        Arrays.sort(durations);

        for (int i = 0; i <this.songs.length ; i++) {
            for (int j = 0; j <this.songs.length ; j++) {
                if (durations[i]==this.songs[j].getDuration()) {
                SortedDuration[i]=this.songs[j];
                d=j;
                }
                }
            if (i>1){
                if (SortedDuration[i].isEqual(SortedDuration[i-1])){
                    SortedDuration[i-1]=this.songs[d-1];
                }
            }
            }



        if (isAscending) return SortedDuration;
        else {Song[] sortedDescending = new Song[SortedDuration.length];
            for (int i = this.songs.length - 1; i >= 0; i--) {
                sortedDescending[songs.length - 1 - i] = SortedDuration[i];
            }
            return sortedDescending;
        }
    }

    public Song[] sortByReleaseYear(boolean isAscending){
        //similar to sortByDuraiton
        int[] releaseYears=new int[this.songs.length];
        Song[] sortedYears = new Song[this.songs.length];

        for (int i = 0; i < this.songs.length; i++) {
            releaseYears[i]=this.songs[i].getReleaseYear();
        }
        Arrays.sort(releaseYears);

        for (int i = 0; i <this.songs.length ; i++) {
            for (int j = 0; j <this.songs.length ; j++) {
                if (releaseYears[i]==this.songs[j].getReleaseYear()) {
                    sortedYears[i]=this.songs[j];
                }
            }
        }



        if (isAscending) return sortedYears;
        else {Song[] sortedDescenging = new Song[sortedYears.length];
            for (int i = this.songs.length - 1; i >= 0; i--) {
                sortedDescenging[songs.length - 1 - i] = sortedYears[i];
            }
            return sortedDescenging;
        }
    }

    public Song[] sortByPopularity(boolean isAscending){
        int[] likes=new int[this.songs.length];
        Song[] sortedLikes = new Song[this.songs.length];
        int d=0;

        for (int i = 0; i < this.songs.length; i++) {
            likes[i]=this.songs[i].getLikes();
        }
        Arrays.sort(likes);

        for (int i = 0; i <this.songs.length ; i++) {
            for (int j = 0; j <this.songs.length ; j++) {
                if (likes[i]==this.songs[j].getLikes()) {
                    sortedLikes[i]=this.songs[j];
                    d=j;
                }
                //is songs have same num of likes then one may get repeated twice, but we can check and manually overwrite it
            }
            if (i>1){
                if (sortedLikes[i].isEqual(sortedLikes[i-1])){
                    sortedLikes[i-1]=this.songs[d-1];
                }
            }
        }



        if (isAscending) return sortedLikes;
        else {Song[] sortedDescending = new Song[sortedLikes.length];
            for (int i = this.songs.length - 1; i >= 0; i--) {
                sortedDescending[songs.length - 1 - i] = sortedLikes[i];
            }
            return sortedDescending;
        }
    }
    public int totalLikesInAlbum(){
        int likesInAlbums=0;

        for (int i = 0; i <songs.length ; i++) {
            likesInAlbums+= songs[i].getLikes();
        }

        return likesInAlbums;
    }

    public static Song[] reverse (Song[] songs){
        Song[] reversed = new Song[songs.length];
        for (int i = songs.length - 1; i >= 0; i--) {
            reversed[songs.length - 1 - i] = songs[i];
        }
        return reversed;
    }

    public String toString() {
        return "Title:{" + title + "},Release year:{" + releaseYear + "},songs:{" + "\n" + Arrays.toString(this.songs) + "}";
    }


}



