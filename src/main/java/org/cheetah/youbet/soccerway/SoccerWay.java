/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cheetah.youbet.soccerway;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 *
 * @author edoardo
 */
public class SoccerWay {
    
    public static final String CLUB_DOMESTICS = "http://it.soccerway.com/competitions/club-domestic/";
    public static final String BLOCK_COMPETITION_INDEX_CLUB_DOMESTIC="http://it.soccerway.com/a/block_competitions_index_club_domestic?"
            + "block_id=page_competitions_1_block_competitions_index_club_domestic_4&"
            + "callback_params={\"level\":1}&"
            + "action=expandItem&"
            + "params={\"area_id\":\"8\",\"level\":2,\"item_key\":\"area_id\"}";
    
    public static void main(String[] args) throws UnsupportedEncodingException{
        System.out.println(URLDecoder.decode("http://it.soccerway.com/a/block_competitions_index_club_domestic?block_id=page_competitions_1_block_competitions_index_club_domestic_4&callback_params=%7B%22level%22%3A%222%22%7D&action=expandItem&params=%7B%22area_id%22%3A%229%22%2C%22level%22%3A2%2C%22item_key%22%3A%22area_id%22%7D", "UTF-8"));
    }
    
    
    
}
