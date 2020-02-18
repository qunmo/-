<template>
    <div class="app-container">
        <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="百度地图" name="baidu">
                <router-link to="/BMap">
                    <router-view></router-view>
                </router-link>
            </el-tab-pane>
            <el-tab-pane label="高德地图" name="amap">
               <router-link to="/AMap">
                    <router-view></router-view>
                </router-link>
            </el-tab-pane>
            <el-tab-pane label="腾讯地图" name="tencent">
                <router-link to="/QQMap">
                    <router-view></router-view>
                </router-link>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                activeName: 'baidu'
            }
        },
        components: {},
        mounted() {
        },
        methods: {
            handleClick(tab, event) {
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
