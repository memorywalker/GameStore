from flask import Flask, jsonify, request
import requests

app = Flask(__name__)

#https://steamapi.xpaw.me/#IPlayerService/GetOwnedGames
#https://steamcommunity.com/dev/apikey
STEAM_API_KEY = 'AD6DCEFE42CA59E9486E27D5D6A5E66D'

@app.route('/api/games/<steam_id>', methods=['GET'])
def get_games(steam_id):
    print("recv steam_id:", steam_id)
    url = f'https://api.steampowered.com/IPlayerService/GetOwnedGames/v1/?key={STEAM_API_KEY}&steamid={steam_id}&format=json&include_appinfo=true'
    response = requests.get(url)
    data = response.json()
    for game in data['response']['games']:
        appid = game['appid']
        #game['img_icon_url'] = f"http://media.steampowered.com/steamcommunity/public/images/apps/{appid}/{game['img_icon_url']}.jpg"
        game['img_icon_url'] = f"https://steamcdn-a.akamaihd.net/steam/apps/{appid}/library_600x900_2x.jpg"
        print(game)
    return jsonify(data)

#76561198099917059

if __name__ == '__main__':
    app.run(debug=True)