export const state = () => ({
  detail: {},
})


export const actions = {
  init: function ({state, commit}) {
    new EventSource('http://localhost:8080/cart/democart/subscribe')
      .addEventListener('message', message => commit('set', JSON.parse(message.data)))
  },
  create: function () {
    return fetch('http://localhost:8080/cart/create?cartId=democart&userId=UserDemo')
  },
}

export const mutations = {
  set: (state, detail) => state.detail = detail,
}
