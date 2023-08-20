package pgdp;

import java.text.DecimalFormat;
import java.util.Objects;

public class Song {
    private String title;
    private int releaseYear;
    private int duration;
    private int likes;


    public Song(String title, int releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration=60;
        this.likes=0;
    }  //default constructor

    public Song(String title, int releaseYear, int duration) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration=duration;
        this.likes=0;
    }  //semi default constructor

    public Song(String title, int releaseYear, int duration, int likes) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration=duration;
        this.likes=likes;
    } //full constructor

    public void setDuration(int duration){this.duration=duration;}
    public boolean changeDuration(int duration){
        setDuration(duration);
        if (duration<0 || duration>720 || this.duration==duration) {return false;}
        else {this.duration=duration;
        return true;}
    }

    public void Like(){
        this.likes=this.likes+1;
    }

    public void unLike(){
        if (this.likes<0) this.likes=0;
        this.likes=this.likes-1;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public int getDuration() {
        return duration;
    }

    public int getLikes() {
        return likes;
    }

    public String toString(){
       float DurationMin=((float)duration/60);
        return "Title:"+title+","+"Duration:"+new DecimalFormat("0.00").format(DurationMin)+
                " minutes,Release year:"+releaseYear+",Likes:"+likes+"\n";//without \n, string form does not look nice
    }

    public boolean isEqual(Song other){
        return Objects.equals(this.title, other.title) && Objects.equals(this.releaseYear, other.releaseYear)&& Objects.equals(this.duration, other.duration) ;
    }

}
