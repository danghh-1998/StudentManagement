<template>
    <div
        id="app"
    >
        <vuescroll :ops="ops">
            <router-view :key="$route.fullPath"/>
        </vuescroll>
    </div>
</template>

<script>
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import vuescroll from 'vuescroll';

export default {
    name: 'App',
    components: {vuescroll},
    data: function () {
        return {
            ops: {
                vuescroll: {},
                scrollPanel: {},
                rail: {},
                bar: {
                    background: '#888888',
                    keepShow: true,
                }
            }
        }
    },
    methods: {}
};
</script>

<style>
#app {
    font-family: 'Montserrat', sans-serif;
    height: 100vh;
    user-select: none;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #2c3e50;
}
</style>