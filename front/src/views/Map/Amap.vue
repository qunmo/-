<template>
    <div class="app-container">
        <div class="amap-page-container">
            <el-amap ref="map" vid="amapDemo" :amap-manager="amapManager" :center="center" :zoom="zoom" :plugin="plugin" :events="events" class="amap-demo">
            </el-amap>
            <!-- <div class="toolbar">
                <button @click="getMap()">get map</button>
            </div> -->
        </div>
    </div>
</template>

<script>
import VueAMap from "vue-amap";
    let amapManager = new VueAMap.AMapManager();
    module.exports = {
        data: function() {
            return {
                amapManager,
                zoom: 12,
                center: [121.59996, 31.197646],
                events: {
                    init: (o) => {
                        console.log(o.getCenter())
                        console.log(this.$refs.map.$$getInstance())
                        o.getCity(result => {
                            console.log(result)
                        })
                    },
                    'moveend': () => {},
                    'zoomchange': () => {},
                    'click': (e) => {
                        alert('map clicked');
                    }
                },
                plugin: ['ToolBar', {
                    pName: 'MapType',
                    defaultType: 0,
                    events: {
                        init(o) {
                            console.log(o);
                        }
                    }
                }]
            };
        },
        methods: {
            getMap() {
                // amap vue component
                console.log(amapManager._componentMap);
                // gaode map instance
                console.log(amapManager._map);
            }
        }
    };
</script>

<style scoped lang="scss">
    .amap-wrapper {
        width: 100%;
        height: 450px;
    }
    .amap-demo {
        height: 450px;
    }
</style>
