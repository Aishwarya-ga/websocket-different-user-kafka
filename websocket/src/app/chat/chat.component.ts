import { Component, OnInit } from '@angular/core';
import Stomp from 'stompjs';
import SockJS from 'sockjs-client';
import $ from 'jquery';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent {

  public title = 'WebSockets chat';
  private stompClient;

  public message : string;

  private  sessionId = Math.random();


  constructor() {
    this.initializeWebSocketConnection();
  }



  initializeWebSocketConnection() {
 
    let id = this.sessionId+"";
    const socket = new SockJS('http://localhost:8080/socket');
    this.stompClient = Stomp.over(socket);

    const _this = this;
    this.stompClient.connect({}, function (frame) {
      _this.stompClient.subscribe("/topic/public/"+id, (message) => {
        if (message.body) {
          $(".chat").append("<div class='message'>" + JSON.parse(message.body).content + "</div>")
          console.log(message.body);
        }
      });
    });
  }


  sendMessage(message) {
    /* (void) send(destination, headers = {}, body = '')
     *     Send a message to a named destination.
     */
    let id = this.sessionId+"";
    var chatMessage = {
      sender : id,
      content : message,
     
    };
    this.stompClient.send("/app/chat.send/"+id,{},JSON.stringify(chatMessage));
     this.message= null;
  }

  
  

}

