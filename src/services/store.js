/**
 * Vuex store example
 */
export default {
    /** Application state */
    state: {
        allGenres: ['Rock','Pop','Hip Hop', 'Classic', 'Jazz', 'R&B'],
        allArtists: ['Drake', '21 Savage', 'Gunna', 'Metro Boomin', 'The Weeknd']
    },

    /** Methods that read the application state */
    getters: {
        friendlyCounter(state) {
            return `counter = ${state.counter}`
        },
    },

    /** Methods that change the application state synchronously */
    mutations: {
        setCounter(state, counter) {
            state.counter = counter
        },

        count(state) {
            state.counter++
        },
    },

    /** Methods that change the application state asynchronously */
    actions: {
        countLater({ commit }) {
            setTimeout(() => { commit('count')}, 1000)
        },
    },


}
