<template>
  <v-layout row justify-center>
    <v-flex xs12 sm6 offset-sm3 >
      <v-card v-if="cart.id">
        <v-toolbar color="blue" dark>
          <v-toolbar-side-icon></v-toolbar-side-icon>
          <v-toolbar-title>Cart</v-toolbar-title>
          <v-spacer></v-spacer>
          <v-icon>shopping_cart</v-icon>
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
        <v-btn color="blue" dark block @click="create">Create Cart</v-btn>
      </div>
    </v-flex>
  </v-layout>
</template>

<script>
  import {mapState, mapActions} from 'vuex'


  export default {
    async fetch({store}) {
      await store.dispatch('cart/init')
    },
    computed: {
      ...mapState('cart', {
        cart: state => state.detail,
      }),
    },
    methods: {
      ...mapActions({
        create: 'cart/create',
      }),
    },
  }
</script>
