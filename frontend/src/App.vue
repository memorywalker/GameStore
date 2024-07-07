<script>
import axios from 'axios';

export default {
  data() {
    return {
      steamId: '',
      games: null,
      loading:false,
      error: '',
    };
  },
  methods: {
    async fetchGames() {
      this.loading = true;
      this.error = '';
      try {
        const response = await axios.get(`/api/games/${this.steamId}`);
        this.games = response.data.response.games;
      }
      catch ( error ) {
        this.error = 'Error fetching games. Please try again later.';
      } 
      finally {
        this.loading = false;
      }
    },
  },
};

</script>

<template>
  <v-app>
    <v-container>
      <v-row justify="center">
        <v-col cols="12" md="8">
          <v-text-field v-model="steamId" label="Steam ID" outlined></v-text-field>
          <v-btn  @click="fetchGames" color="primary" class="mt-4">Fetch Games</v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-col v-if="error" cols="12">
          <v-alert  type="error" dismissible>{{ error }}</v-alert>
        </v-col>
        <v-col v-if="loading" cols="12" class="text-center">
          <v-progress-circular indeterminate color="primary"></v-progress-circular>
        </v-col>
        <v-col v-for="game in games" :key="game.appid" cols="12" sm="6" md="4" lg="3">
          <v-card class="game-cover">
            <v-img :src="game.img_icon_url" alt="Game Cover" aspect-ratio="0.5"></v-img>
            <v-card-title class="game-title">{{ game.name }}</v-card-title>
            <v-card-subtitle class="game-details">Playtime:{{ game.playtime_forever }} hours</v-card-subtitle>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-app>  
</template>

<style >
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.game-card {
  margin-bottom: 20px;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 4px 8px rgba(0,0,0,.14);
  transition: transform 0.2s;
}

.game-cover {
  height: 500px;
  object-fit: cover;
}

.game-title {
  font-size: 18px;
  font-weight: bold;
}
.game-details {
  font-size: 14px;
  color:gray;
}

.v-card {
  margin-bottom: 20px;
}
</style>
