<template>
    <div class="app-container">
        <baidu-map class="bm-view" :center="center" :zoom="zoom" @ready="handler">
            <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>
            <bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :showAddressBar="true" :autoLocation="true"></bm-geolocation>
        </baidu-map>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                center: {
                    lng: 0,
                    lat: 0
                },
                zoom: 3
            };
        },
        components: {},
        mounted() {
        },
        methods: {
            handler({
                BMap,
                map
            }) {
                console.log(BMap, map)
                this.center.lng = 116.404
                this.center.lat = 39.915
                this.zoom = 15
            }
        }
    };
</script>

<style scoped lang="scss">
    .bm-view {
        width: 100%;
        height: 450px;
    }
</style>
