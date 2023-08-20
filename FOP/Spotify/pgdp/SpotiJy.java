package pgdp;


public class SpotiJy {
    private Artist[] artists;

    public SpotiJy(){
        this.artists=new Artist[]{};
    }

    public SpotiJy(Artist[] artists){
        this.artists=artists;
    }

    public Artist[] getArtists(){
        return artists;
    }

    private Artist[] removeDuplicates(Artist[] artists){
        //I am assuming that no nulls are in the give array of artists
        int a =0;
        int c=0;
        int d=0;
        Artist[] duplicate = artists.clone();
        Artist[] firstFilter=new Artist[artists.length];
        Song[] ab=new Song[]{};
        Album[] acb=new Album[]{};
        Artist adada=new Artist("adanid231289511$%u&*#&@(", "$%u&*#&@", 0, acb, ab );


        for (int i = 0; i < duplicate.length; i++) {
            a=0;
            for (int j = 0; j < artists.length  ; j++) {

                if (duplicate[j].isEqual(artists[i])) {
                    d=j;
                    duplicate[j]=adada;
                    a++;
                }
            }
            if (a==1){
                firstFilter[c]=artists[i];
                c++;
            }
            if (a>1){
                firstFilter[c]=artists[d];
                c++;
            }
        }


        Artist[] filter=new Artist[c];
        for (int i = 0; i <c ; i++) {
            filter[i]=firstFilter[i];
        }



        return filter;
    }

    public void addArtists(Artist[] artists){
        //same code as addSongs but names and classes are changed

        int a=0;
        for (int i = 0; i < this.artists.length ; i++) {
            if (this.artists[i] == null) {
                a++;
            }
        }
            if (a==this.artists.length){
                artists=removeDuplicates(artists);
                this.artists=artists;
            }else {

                artists = removeDuplicates(artists);
                int NumAddedArtists = artists.length;
                int b = artists.length;
                a = 0;
                Artist[] filteredArtists = new Artist[artists.length];

                for (Artist song : artists) {
                    for (Artist value : this.artists) {
                        if (value.isEqual(song))
                            NumAddedArtists--;
                    }
                    if (NumAddedArtists == b) {
                        filteredArtists[a] = song;
                        a++;
                    }
                    b = NumAddedArtists;
                }

                Artist[] ArtistFinal = new Artist[this.artists.length + NumAddedArtists];
                for (int i = 0; i < this.artists.length; i++) {
                    ArtistFinal[i] = this.artists[i];
                }
                for (int j = 0; j < NumAddedArtists; j++) {
                    ArtistFinal[this.artists.length + j] = filteredArtists[j];
                }
                this.artists = ArtistFinal;
            }
    }

    public String [] getTopTrendingArtist(){
        long mostLikes=0;
        int a=0;
        for (int i = 0; i < artists.length ; i++) {
            if (artists[i].totalLikes()>mostLikes) {
                mostLikes = artists[i].totalLikes();
                a=i;
            }
        }

        String [] TopArtist=new String[2];
        String name="['"+artists[a].getFirstName()+", ";
        String lastName="'"+artists[a].getLastname()+"']";
        TopArtist[0]=name;
        TopArtist[1]=lastName;

        return TopArtist;
    }

    public String getTopTrendingAlbum(){
        long TopLikes=0;
        Song[] tempsongs=new Song[0];
        Album tempAlbum=new Album("temp", 0, tempsongs);
        tempAlbum=artists[0].mostLikedAlbum();

        for (int i = 0; i <artists.length ; i++) {
            if (artists[i].mostLikedAlbum().totalLikesInAlbum()>tempAlbum.totalLikesInAlbum()){
                tempAlbum=artists[i].mostLikedAlbum();
            }
        }

        return tempAlbum.getTitle();
    }

    public String getTopTrendingSong(){
        Song tempSong=artists[0].mostLikedSong();
        int a=0;

        for (int i = 0; i < artists.length ; i++) {
            if (artists[i].mostLikedSong().getLikes()>tempSong.getLikes()){
                tempSong=artists[i].mostLikedSong();
            }

        }



        return tempSong.getTitle();
    }


}

