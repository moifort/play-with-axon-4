export const state = () => ({
  detail: {},
  isLoading: false,
})


export const actions = {
  init: function ({commit}) {
    new EventSource('http://localhost:8080/cart/demo-cart/subscribe')
      .addEventListener('message', message => {
        commit('isLoading', false)
        commit('set', JSON.parse(message.data))
      })
  },
  create: async function ({commit}) {
    commit('isLoading', true)
    try {
      await fetch('http://localhost:8080/cart/create?cartId=demo-cart&userId=UserDemo')
    } catch (e) {
      commit('isLoading', false)
    }
  },
  add: async function ({commit}, {name, quantity, price}) {
    commit('isLoading', true)
    try {
      await fetch(`http://localhost:8080/cart/add?cartId=demo-cart&name=${name}&quantity=${quantity}&unitPrice=${price}`)
    } catch (e) {
      commit('isLoading', false)
    }
  },
}

export const mutations = {
  set: (state, detail) => state.detail = detail,
  isLoading: (state, isLoading) => state.isLoading = isLoading,
}
