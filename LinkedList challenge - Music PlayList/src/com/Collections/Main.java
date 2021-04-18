package com.javatim;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

    private static LinkedList<Album> albums = new LinkedList<>();

    public static void main(String[] args) {
	// write your code here

        Album album = new Album("Jersey","DSP");
        album.addSong("Spirit of jersey",4.5);
        album.addSong("Bajarangabali",3.5 );
        album.addSong("Oh my god",4.1);

        albums.add(album); // Adding the album to the List of albums

        album = new Album("Ala vaikuntapuram lo","Thaman");
        album.addSong("ButtaBomma",4.6);
        album.addSong("Samajavaragamana",3.8);
        album.addSong("Sitarala Sirapadu",3.2);

        albums.add(album);  // Adding the album to the List of albums


        // creating playList and adding some songs to the playList

        LinkedList<Song> playList = new LinkedList<>();
        albums.get(0).addToPlayList("Oh my god",playList);
        albums.get(0).addToPlayList("Spirit of jersey",playList);
        albums.get(0).addToPlayList("Bajarangabali",playList);
        albums.get(0).addToPlayList("Jaffa",playList); // does not Exist

        albums.get(1).addToPlayList(1,playList);
        albums.get(1).addToPlayList(2,playList);
        albums.get(1).addToPlayList(3,playList);
        albums.get(1).addToPlayList(10,playList ); // there is no track no.

        play(playList);
    }

    public static void play(LinkedList<Song> playList){

        Scanner sc = new Scanner(System.in);
        boolean quit =true;
        boolean forward = true;
        ListIterator<Song> listIterator = playList.listIterator();

        if(playList.isEmpty()){
            System.out.println("There are no songs in the PlayList");
        }
        else
        {
            System.out.println("Currently playing "+listIterator.next().toString());
            printMenu();
        }

        while(quit){

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 0:
                    System.out.println("Playlist Completed");
                    quit = false;
                    break;

                case 1:
                    if(!forward){
                        if(listIterator.hasNext()){
                            listIterator.next();
                        }
                        forward=true;
                    }
                    if(listIterator.hasNext()) {
                        System.out.println("Currently Playing " + listIterator.next().toString());
                    }
                    else
                        System.out.println("Reached End of the PlayList");

                    break;

                case 2:

                    if(forward){
                        if(listIterator.hasPrevious()){
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if(listIterator.hasPrevious()){
                        System.out.println("Currently Playing "+ listIterator.previous().toString());
                    }
                    else
                        System.out.println("You are at the start of the PlayList");

                    break;
                case 3:
                    if(forward){
                        if(listIterator.hasPrevious()){
                            System.out.println("Replaying the song "+listIterator.previous());
                            forward = false;
                        }
                        else
                            System.out.println("We are at the start of the List");
                    }
                    else{
                        if(listIterator.hasNext()){
                            System.out.println("Replaying the song "+listIterator.next());
                            forward = true;
                        }
                        else
                            System.out.println("We are at the End of the List");

                    }
                    break;
                case 4:
                    printPlayList(playList);
                    break;
                case 5:
                    printMenu();
                    break;


                case 6:
                    if(playList.size() > 0){
                        listIterator.remove();
                        if(listIterator.hasNext()){
                            System.out.println("Now Currently playing "+listIterator.next());

                        }else if(listIterator.hasPrevious()){
                            System.out.println("Now Currently Playing "+listIterator.previous());
                        }
                    }
                    break;

            }

        }
    }

    private static void printMenu(){
        System.out.println("Available Actions \nPRESS\n"+
                "0 - To Quit\n"+
                "1 - To Play next song\n"+
                "2 - To Play previous Song\n"+
                "3 - To Replay the song\n"+
                "4 - List of songs in the playList\n"+
                "5 - To print Menu\n"+
                "6 - To Remove the Song from the List");
    }

    private static void printPlayList(LinkedList<Song> list){
        Iterator<Song> iterator = list.iterator();
        System.out.println("===================================");
        while(iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }
        System.out.println("====================================");
    }
}
