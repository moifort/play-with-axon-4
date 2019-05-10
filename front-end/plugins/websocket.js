import SockJS from 'sockjs-client'
import Stomp from 'webstomp-client'

export default async (ctx) => {
  const {app, store} = ctx
  const socket = new SockJS("http://localhost:8080/gs-guide-websocket")
  const stompClient = Stomp.over(socket)
  await new Promise((resolve, reject) => stompClient.connect({}, frame => resolve(frame), error => reject(error)))
  stompClient.subscribe('/topic/cart/test', message => console.log('super moi:', message.body))
  // app.$algolia = Vue.prototype.$algolia
  // ctx.$algolia = Vue.prototype.$algolia
  // store.$algolia = Vue.prototype.$algolia
}
