package com.epam.rd.autocode.startegy.cards;

import java.util.*;

public class CardDealingStrategies {
        public static CardDealingStrategy texasHoldemCardDealingStrategy() {
            return (deck, players) -> {

                Map<String, List<Card>> game = new HashMap<>();

                //deal cards to players 2 times
                for (int j = 0; j < 2; j++) {
                    for (int i = 1; i <= players; i++) {
                        if (game.get("Player " + i) == null) {
                            game.put("Player " + i, new ArrayList<>(Collections.singletonList(deck.dealCard())));
                        } else {
                            List<Card> tempDeck = new ArrayList<>(game.get("Player " + i));
                            tempDeck.add(deck.dealCard());
                            game.put("Player " + i, tempDeck);
                        }
                    }
                }

                //community
                List<Card> communityDeck = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    communityDeck.add(deck.dealCard());
                }
                game.put("Community", communityDeck);


                //remaining
                game.put("Remaining", deck.restCards());




                return game;
            };
        }

    public static CardDealingStrategy classicPokerCardDealingStrategy() {
        return (deck, players) -> {

            Map<String, List<Card>> game = new HashMap<>();

            //deal cards to players 5 times
            for (int j = 0; j < 5; j++) {
                for (int i = 1; i <= players; i++) {
                    if (game.get("Player " + i) == null) {
                        game.put("Player " + i, new ArrayList<>(Collections.singletonList(deck.dealCard())));
                    } else {
                        List<Card> tempDeck = new ArrayList<>(game.get("Player " + i));
                        tempDeck.add(deck.dealCard());
                        game.put("Player " + i, tempDeck);
                    }
                }
            }

            //remaining
            game.put("Remaining", deck.restCards());


            return game;
        };
    }

    public static CardDealingStrategy bridgeCardDealingStrategy(){
        return (deck, players) -> {
            Map<String, List<Card>> game = new HashMap<>();

            //deal cards to players 13 times
            for (int j = 0; j < 13; j++) {
                for (int i = 1; i <= players; i++) {
                    if (game.get("Player " + i) == null) {
                        game.put("Player " + i, new ArrayList<>(Collections.singletonList(deck.dealCard())));
                    } else {
                        List<Card> tempDeck = new ArrayList<>(game.get("Player " + i));
                        tempDeck.add(deck.dealCard());
                        game.put("Player " + i, tempDeck);
                    }
                }
            }

            return game;
        };
    }

    public static CardDealingStrategy foolCardDealingStrategy(){
        return (deck, players) -> {
            Map<String, List<Card>> game = new HashMap<>();

            //deal cards to players 6 times
            for (int j = 0; j < 6; j++) {
                for (int i = 1; i <= players; i++) {
                    if (game.get("Player " + i) == null) {
                        game.put("Player " + i, new ArrayList<>(Collections.singletonList(deck.dealCard())));
                    } else {
                        List<Card> tempDeck = new ArrayList<>(game.get("Player " + i));
                        tempDeck.add(deck.dealCard());
                        game.put("Player " + i, tempDeck);
                    }
                }
            }

            //trump card
            game.put("Trump card", new ArrayList<>(Collections.singletonList(deck.dealCard())));

            //remaining
            game.put("Remaining", deck.restCards());



            return game;
        };
    }

}
