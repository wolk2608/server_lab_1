function getIndex(gameInCartList, id) {
    for (var i = 0; i < gameInCartList.length; i++) {
        if (gameInCartList[i].game.id === id) {
            return i;
        }
    }
    return -1;
}


var cartApi = Vue.resource('/cart{/id}');
var cartList = Vue.resource('/cart/getList');

Vue.component('cart-form', {
    props: ['gameInCartList'],
    data: function() {
        return {
            //resCostAll: ""
        }
    },
    template: '<div>' +
        //'<h5>К оплате {{ resCostAll }} руб. </h5>' +
        '<input class="btn btn-secondary mb-5" formmethod="post" type="button" value="Купить" @click="buy"/>' +
        '</div>',

    methods: {
        buy: function() {
            cartApi.save().then(result => {
                if (result.ok) {
                    var i = this.gameInCartList.length;
                    this.gameInCartList.splice(0, i);
                }
            });
        }
    }
});

Vue.component('gameInCart-row', {
    props: ['gameInCart', 'gameInCartList'],
    data: function() {
        return {
            id: this.gameInCart.game.id,
            count: this.gameInCart.count,
        }
    },
    template:   '<tr>' +
                        '<th scope="row">{{ gameInCart.game.id }}</th>' +
                        '<td>{{ gameInCart.game.name }}</td>' +
                        '<td>{{ gameInCart.count }}' +
                            '<input class="btn btn-secondary mx-1 w-15" type="button" value="+" @click="increase"/>' +
                            '<input class="btn btn-secondary w-15" type="button" value="-" @click="decrease"/>' +
                        '</td>' +
                        '<td>{{ gameInCart.game.cost * gameInCart.count}} руб.</td>' +
                        '<td><input class="btn btn-warning" type="button" value="Удалить" @click="del"/></td>' +
                    '</tr>',
    methods: {
        decrease: function() {
            this.gameInCart.count--;
            cartApi.update({id: this.gameInCart.game.id}, this.gameInCart).then(result =>
                result.json().then(data => {
                    var index = getIndex(this.gameInCartList, data.game.id);
                    this.gameInCartList.splice(index, 1, data);
                })
            )
        },
        increase: function() {
            this.gameInCart.count++;
            cartApi.update({id: this.gameInCart.game.id}, this.gameInCart).then(result =>
                result.json().then(data => {
                    var index = getIndex(this.gameInCartList, data.game.id);
                    this.gameInCartList.splice(index, 1, data);
                })
            )
        },
        del: function() {
            cartApi.remove({id: this.id}).then(result => {
                if (result.ok) {
                    var index = getIndex(this.gameInCartList, this.gameInCart);
                    this.gameInCartList.splice(index, 1);
                }
            })
        }
    }
});

Vue.component('gameInCartList', {
    props: ["gameInCartList"],
    data: function () {
        return {
            gameInCart: null
        }
    },
    template: '<div class="ms-5 me-5">' +
        '<cart-form :gameInCartList="gameInCartList"></cart-form>' +
        '<table class="table table-bordered table-hover table-sm">' +
        '<thead>' +
        '<tr>' +
            '<th>Id</th>' +
            '<th>Название игры</th>' +
            '<th>Количество</th>' +
            '<th>Цена</th>' +
            '<th></th>' +
        '</tr>' +
        '</thead>' +
        '<tbody>' +
        '<gameInCart-row v-for="gameInCart in gameInCartList" :key="gameInCart.game.id" :gameInCart="gameInCart" :gameInCartList="gameInCartList"></gameInCart-row>' +
        '</tbody>' +
        '</table>' +
        '</div>',
    created: function () {
        cartList.get().then(result =>
            result.json().then(data =>
                data.forEach(gameInCart => this.gameInCartList.push(gameInCart))
            )
        )
    },
});

var app = new Vue({
    el: '#app',
    template: '<gameInCartList :gameInCartList="gameInCartList" />',
    data: {
        gameInCartList: []
    }
});
