<template>
  <v-layout row wrap>
    <v-flex xs12 sm6>
      <v-card v-if="cart.id">
        <v-toolbar color="purple" dark>
          <v-toolbar-title>Product</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-icon>shopping_basket</v-icon>
        </v-toolbar>
        <v-list>
          <v-list-tile avatar>
            <v-list-tile-avatar tile>
              <img src="https://media.cdnws.com/_i/96147/4991/1522/95/bouton-chaussette-homme.jpeg">
            </v-list-tile-avatar>
            <v-list-tile-content>
              <v-list-tile-title>Socks</v-list-tile-title>
              <v-list-tile-sub-title>10€</v-list-tile-sub-title>
            </v-list-tile-content>

            <v-list-tile-action @click="addProduct({name: 'Socks', quantity: 1, price: 10})">
              <v-btn icon ripple>
                <v-icon color="grey lighten-1">add_circle</v-icon>
              </v-btn>
            </v-list-tile-action>
          </v-list-tile>
          <v-list-tile avatar>
            <v-list-tile-avatar tile>
              <img src="http://img.over-blog-kiwi.com/2/41/52/55/20170826/ob_eea4a0_from-petit-basque-decoupe.jpg">
            </v-list-tile-avatar>
            <v-list-tile-content>
              <v-list-tile-title>Cheese</v-list-tile-title>
              <v-list-tile-sub-title>4.5€</v-list-tile-sub-title>
            </v-list-tile-content>

            <v-list-tile-action @click="addProduct({name: 'Cheese', quantity: 1, price: 4.5})">
              <v-btn icon ripple>
                <v-icon color="grey lighten-1">add_circle</v-icon>
              </v-btn>
            </v-list-tile-action>
          </v-list-tile>
          <v-list-tile avatar>
            <v-list-tile-avatar tile>
              <img
                src="https://www.cave-spirituelle.com/media/catalog/product/cache/1/image/470x/17f82f742ffe127f42dca9de82fb58b1/3/_/3_bls_grands_bdx-2_2.png">
            </v-list-tile-avatar>
            <v-list-tile-content>
              <v-list-tile-title>Wines</v-list-tile-title>
              <v-list-tile-sub-title>72€</v-list-tile-sub-title>
            </v-list-tile-content>

            <v-list-tile-action @click="addProduct({name: 'Wines', quantity: 1, price: 72})">
              <v-btn icon ripple>
                <v-icon color="grey lighten-1">add_circle</v-icon>
              </v-btn>
            </v-list-tile-action>
          </v-list-tile>
          <v-list-tile avatar>
            <v-list-tile-avatar tile>
              <img
                src="https://assets.pcmag.com/media/images/340604-apple-macbook-pro-13-inch-2013.jpg?width=1000&height=791">
            </v-list-tile-avatar>
            <v-list-tile-content>
              <v-list-tile-title>MacBook</v-list-tile-title>
              <v-list-tile-sub-title>1092€</v-list-tile-sub-title>
            </v-list-tile-content>

            <v-list-tile-action @click="addProduct({name: 'MacBook', quantity: 1, price: 1092})">
              <v-btn icon ripple>
                <v-icon color="grey lighten-1">add_circle</v-icon>
              </v-btn>
            </v-list-tile-action>
          </v-list-tile>
        </v-list>
      </v-card>
    </v-flex>
    <v-flex xs12 sm6>
      <v-card v-if="cart.id">
        <v-toolbar color="blue" dark>
          <v-toolbar-title>Cart</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-btn :loading="isLoading" flat icon>
            <v-icon light>shopping_cart</v-icon>
          </v-btn>
        </v-toolbar>
        <v-list>
          <template v-for="(item, index) in cart.products">
            <v-list-tile :key="index">
              <v-list-tile-content>
                <v-list-tile-title>{{ item.name }}</v-list-tile-title>
                <v-list-tile-sub-title class="text--primary">{{ item.headline }}</v-list-tile-sub-title>
                <v-list-tile-sub-title>{{ item.unitPrice }} € - total: {{ item.quantity * item.unitPrice }} €
                </v-list-tile-sub-title>
              </v-list-tile-content>

              <v-list-tile-action>
                <v-list-tile-action-text>x{{ item.quantity }}</v-list-tile-action-text>
              </v-list-tile-action>

            </v-list-tile>
            <v-divider></v-divider>
          </template>
          <v-list-tile>
            <v-list-tile-content>
              <v-list-tile-title>Total: {{ cart.totalPrice }} €</v-list-tile-title>
            </v-list-tile-content>
            <v-list-tile-action>
              <v-list-tile-action-text>x{{ cart.numberOfProduct }}</v-list-tile-action-text>
            </v-list-tile-action>
          </v-list-tile>
        </v-list>
      </v-card>
      <div v-else>
        <v-btn color="blue" dark block @click="create"
               :loading="isLoading">Create Cart
          <v-icon right dark>add_shopping_cart</v-icon>
        </v-btn>
      </div>
    </v-flex>
  </v-layout>
</template>

<script>
  import {mapState, mapActions} from 'vuex'


  export default {
    data() {
      return {
        loading: false,
      }
    },
    async fetch({store}) {
      await store.dispatch('cart/init')
    },
    computed: {
      ...mapState('cart', {
        cart: state => state.detail,
        isLoading: state => state.isLoading,
      }),
    },
    methods: {
      ...mapActions({
        create: 'cart/create',
        addProduct: 'cart/add'
      }),
    },
  }
</script>
