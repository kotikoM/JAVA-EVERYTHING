package pgdp;

import java.util.Objects;

public class Artist {
    private String firstName;
    private String lastname;
    private int birthYear;
    private Album[] albums;
    private Song[] singles;

    public Artist(String firstName, String lastname, int birthYear, Album[] albums, Song[] singles ){
        this.firstName=firstName;
        this.lastname=lastname;
        this.birthYear=birthYear;
        this.albums=albums;
        this.singles=singles;
    }

    public String getFirstName(){return firstName;}
    public String getLastname(){return lastname;}
    public int getBirthYear(){return birthYear;}
    public Album[] getAlbums(){return albums;}
    public Song[] getSingles(){return singles;}
    public int numOfAlbums(){return albums.length;}



    public Song mostLikedSong(){
        int[] mostLikedInSingles=new int[1];
        int[] mostLikedInAlbum = new int[1];
        Song MostLikedSingle=this.singles[0];
        Song mostLikedOne=this.singles[0];
        Song[] Singles=this.singles.clone();
        Song[] MostPopularFromAlbums=new Song[albums.length];


        //most liked in singles
        for (Song single : singles) {
            if (single.getLikes() > MostLikedSingle.getLikes()) {
                MostLikedSingle = single;
            }
        }

        //most liked in albums
        for (int i = 0; i < albums.length; i++)
         {
             Song[] temporary=new Song[albums[i].getSongs().length];
            temporary = albums[i].sortByPopularity(false);
            if (temporary[0].getLikes() > mostLikedOne.getLikes())
                mostLikedOne = temporary[0];
        }

        //compare them
        if (MostLikedSingle.getLikes()>mostLikedOne.getLikes()){
            mostLikedOne=MostLikedSingle;
        }


        return mostLikedOne;
    }

    public Song leastLikedSong(){
        //very similar to mostLikedSong (> is <) and names are changed
        int[] leastLikedInAlbum = new int[1];
        Song leastLikedSingle=this.singles[0];
        Song leastLikedOne=this.singles[0];

        Song[] Singles=this.singles.clone();

        Song[] leastPopularFromAlbums=new Song[albums.length];


        for (Song single : singles) {
            if (single.getLikes() < leastLikedSingle.getLikes() || single.getLikes()==0) {
                leastLikedSingle = single;
            }
        }

        for (int i = 0; i < albums.length; i++)
        {
            Song[] temporary=new Song[albums[i].getSongs().length];
            temporary = albums[i].sortByPopularity(false);
            if (temporary[0].getLikes() < leastLikedOne.getLikes())
                leastLikedOne = temporary[0];
        }



        if (leastLikedSingle.getLikes()<leastLikedOne.getLikes()){
            leastLikedOne=leastLikedSingle;
        }


        return leastLikedOne;
    }

    public int totalLikes(){
        int likesInSingles=0;
        int likesInAlbums=0;



        for (Song single : singles) {
            likesInSingles = likesInSingles + single.getLikes();
        }

        for (Album album : albums) {
            Song[] temporary = new Song[album.getSongsLength()];
            temporary = album.getSongs();
            for (int j = 0; j < temporary.length; j++) {
                likesInAlbums = likesInAlbums + temporary[j].getLikes();
            }
        }


        return likesInSingles+likesInAlbums;

    }


    public Album mostLikedAlbum(){
        int likesInAlbums=0;
        int a=0;

        for (int i = 0; i <albums.length ; i++) {
            if (albums[i].totalLikesInAlbum()>likesInAlbums){
                likesInAlbums=albums[i].totalLikesInAlbum();
                a=i;
            }
        }

        return albums[a];
    }

    public boolean isEqual(Artist other){
        return (Objects.equals(this.firstName, other.firstName) && Objects.equals(this.lastname, other.lastname) && this.birthYear==other.birthYear);
    }


    public String toString(){
        return "'Name: {"+firstName+"} {"+lastname+"},Birth year:{"+birthYear+"},Total likes:{"+totalLikes()+"}'";
    }



}
