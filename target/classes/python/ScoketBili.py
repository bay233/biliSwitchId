from urllib import request
import json
import socket

def open_url(url):
    head = {}
    head[
        'User-Agent'] = 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36'
    req = request.Request(url, headers=head)
    response = request.urlopen(req)
    html = response.read()
    return html


def switchId(apiParameter):
    apiHeard = 'https://api.bilibili.com/x/web-interface/view?'
    api = apiHeard + apiParameter
    data = json.loads(open_url(api).decode('utf-8'))
    Id = ''
    if apiParameter.split('=')[0] == 'aid':
        Id = data['data']['bvid']
    elif apiParameter.split('=')[0] == 'bvid':
        Id = data['data']['aid']
    return Id

def ScoketServer():
    # 创建 socket 对象
    serversocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    # 获取本地主机名
    host = "localhost"
    port = 33222
    # 绑定端口号
    serversocket.bind((host, port))
    # 设置最大连接数，超过后排队
    serversocket.listen(5)
    # print('开始接受请求，本机host:', host)
    print("启动成功")
    while True:
        try:
            # 建立客户端连接
            clientsocket, addr = serversocket.accept()
            # print("clientsocket:", clientsocket)
            data = clientsocket.recv(1024)
            data = str(data, encoding="utf-8")
            # print("data:", data)
            # print("连接地址: %s" % str(addr))
            try:
                msg = str(switchId(data))
            except:
                msg = "0"
            clientsocket.send(msg.encode('utf-8'))
            clientsocket.close()
        except :
            pass

if __name__ == '__main__':
    ScoketServer()