package com.backend.fitchallenge.domain.chat.dto;

import lombok.Getter;

@Getter
public class ChatMessage {

    private MessageType type;
    private String roomId;
    private String sender; //메시지 보낸사람
    private String message;

    // 메시지 타입 : 입장, 채팅
    public enum MessageType {
        ENTER, TALK
    }

    public  void setMessage(String message) {
        this.message = message;
    }
}
