<template>
    <div class="app-nav">
        <b-navbar
            toggleable="lg"
            type="dark"
            variant="dark"
            fixed="top"
        >
            <b-navbar-toggle
                target="nav-collapse"
            />
            <b-collapse
                id="nav-collapse"
                is-nav
            >
                <b-navbar-nav class="m-auto">
                    <b-nav-item>
                        <span
                            class="nav-text"
                            @click="$router.push('/students')"
                        >
                            Học sinh
                        </span>
                    </b-nav-item>
                    <b-nav-item>
                        <span
                            class="nav-text"
                            @click="$router.push('/subjects')"
                        >
                            Môn học
                        </span>
                    </b-nav-item>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>
    </div>
</template>

<script>
import {faCog} from '@fortawesome/free-solid-svg-icons'
import {library} from '@fortawesome/fontawesome-svg-core'

library.add(faCog)
export default {
    name: "AppNavBar",
    computed: {},
    methods: {}
}
</script>

<style scoped>
.app-nav {
}

.nav-text {
    font-size: 18px;
}
</style>