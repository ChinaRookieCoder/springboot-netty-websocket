# websocket实时通信

前台:html和js或Vue实现Websocket客户端(自行实现),通过JSON格式通信，需自行实现客户端心跳(window.setInterval)来防止服务端主动关闭websocket。

websocket插件:vue-native-websocket



后端:只实现了发送消息,接收消息,服务端心跳超时主动关闭websocket连接，可参考思路