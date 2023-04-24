

export default {
    state: {
        status: "matching",// matching, playing, failed
        socket: null,
        opponent_photo: "",
        opponent_username: "",
        gameMap:null,
    },
    getters: {
    },
    mutations: {
        updateSocket(state, socket) {
            state.socket = socket;
        },
        updateOpponent(state, opponent) {
            state.opponent_photo = opponent.photo;
            state.opponent_username = opponent.username;
        },
        updateStatus(state, status) {
            state.status = status;
        },
        updateGameMap(state, gameMap) {
            state.gameMap = gameMap;
        },

    },
    actions: {
    },
    modules: {
    }
}
