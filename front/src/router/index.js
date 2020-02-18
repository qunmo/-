import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'Dashboard', icon: 'dashboard' }
    }]
  },
  {
    path: '/DataShow',
    component: Layout,
    children: [
      {
        path: 'list',
        name: 'DataShow',
        component: () => import('@/views/DataShow/list'),
        meta: { title: '大屏数据展示', icon: 'table' }
      },
      {
        path: 'add',
        name: 'DataShow',
        component: () => import('@/views/DataShow/add'),
        meta: { title: '新增模板', icon: 'table' },
        hidden: true
      }
    ]
  },
  {
    path: '/InputAndOutput',
    component: Layout,
    meta: { title: '导入导出模板', icon: 'nested'},
    children: [
      {
        path: 'model',
        name: 'InputAndOutput',
        component: () => import('@/views/InputAndOutput/Model'),
        meta: { title: '导入导出模板', icon: 'table' }
      },
      {
        path: 'pull',
        name: 'InputAndOutput',
        component: () => import('@/views/InputAndOutput/pull'),
        meta: { title: '拖动效果', icon: 'table' }
      }
    ]
    },
    {
      path: '/onePointLogin',
      component: Layout,
      meta: { title: '单点登录', icon: 'nested'},
      children: [
        {
          path: 'login',
          name: 'login',
          component: () => import('@/views/onePointLogin/login'),
          meta: { title: '登录页面1', icon: 'table' }
        },
        {
          path: 'login2',
          name: 'login2',
          component: () => import('@/views/onePointLogin/login2'),
          meta: { title: '登录页面2', icon: 'table' }
        },
        {
          path: 'success',
          name: 'login',
          component: () => import('@/views/onePointLogin/success'),
          meta: { title: '登录成功', icon: 'table' }
        },
      ]
    },
    {
    path: '/Map',
    component: Layout,
    meta: { title: '第三方地图', icon: 'nested'},
    children: [
      // {
      //   path: 'index',
      //   name: 'index',
      //   component: () => import('@/views/Map/index'),
      //   meta: { title: '第三方地图', icon: 'nested'}
      // },
      {
        path: 'Amap',
        name: 'Amap',
        component: () => import('@/views/Map/Amap'),
        meta: { title: '高德地图', icon: 'nested'},
        // hidden: true
      },
      {
        path: 'BMap',
        name: 'BMap',
        component: () => import('@/views/Map/BMap'),
        meta: { title: '百度地图', icon: 'nested'},
        // hidden: true
      },
      {
        path: 'QQMap',
        name: 'QQMap',
        component: () => import('@/views/Map/QQMap'),
        meta: { title: '腾讯地图', icon: 'nested'},
        // hidden: true
      }
    ]
  },
  {
    path: '/roleManage',
    component: Layout,
    meta: { title: '角色管理', icon: 'nested'},
    children: [
      {
        path: 'manage',
        name: 'manage',
        component: () => import('@/views/roleManage/manage'),
        meta: { title: '角色列表', icon: 'nested'},
        // hidden: true 
      }, 
      {
        path: 'add',
        name: 'add',
        component: () => import('@/views/roleManage/add'),
        meta: { title: '新增角色', icon: 'nested'},
        // hidden: true
      },
      {
        path: 'edit',
        name: 'edit',
        component: () => import('@/views/roleManage/edit'),
        meta: { title: '修改角色', icon: 'nested'},
        // hidden: true
      }
    ]
  },
  // {
  //   path: '/example',
  //   component: Layout,
  //   redirect: '/example/table',
  //   name: 'Example',
  //   meta: { title: 'Example', icon: 'example' },
  //   children: [
  //     {
  //       path: 'table',
  //       name: 'Table',
  //       component: () => import('@/views/table/index'),
  //       meta: { title: 'Table', icon: 'table' }
  //     },
  //     {
  //       path: 'tree',
  //       name: 'Tree',
  //       component: () => import('@/views/tree/index'),
  //       meta: { title: 'Tree', icon: 'tree' }
  //     }
  //   ]
  // },

  // {
  //   path: '/form',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'index',
  //       name: 'Form',
  //       component: () => import('@/views/form/index'),
  //       meta: { title: 'Form', icon: 'form' }
  //     }
  //   ]
  // },

  // {
  //   path: '/nested',
  //   component: Layout,
  //   redirect: '/nested/menu1',
  //   name: 'Nested',
  //   meta: {
  //     title: 'Nested',
  //     icon: 'nested'
  //   },
  //   children: [
  //     {
  //       path: 'menu1',
  //       component: () => import('@/views/nested/menu1/index'), // Parent router-view
  //       name: 'Menu1',
  //       meta: { title: 'Menu1' },
  //       children: [
  //         {
  //           path: 'menu1-1',
  //           component: () => import('@/views/nested/menu1/menu1-1'),
  //           name: 'Menu1-1',
  //           meta: { title: 'Menu1-1' }
  //         },
  //         {
  //           path: 'menu1-2',
  //           component: () => import('@/views/nested/menu1/menu1-2'),
  //           name: 'Menu1-2',
  //           meta: { title: 'Menu1-2' },
  //           children: [
  //             {
  //               path: 'menu1-2-1',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
  //               name: 'Menu1-2-1',
  //               meta: { title: 'Menu1-2-1' }
  //             },
  //             {
  //               path: 'menu1-2-2',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
  //               name: 'Menu1-2-2',
  //               meta: { title: 'Menu1-2-2' }
  //             }
  //           ]
  //         },
  //         {
  //           path: 'menu1-3',
  //           component: () => import('@/views/nested/menu1/menu1-3'),
  //           name: 'Menu1-3',
  //           meta: { title: 'Menu1-3' }
  //         }
  //       ]
  //     },
  //     {
  //       path: 'menu2',
  //       component: () => import('@/views/nested/menu2/index'),
  //       meta: { title: 'menu2' }
  //     }
  //   ]
  // },

  // {
  //   path: 'external-link',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
  //       meta: { title: 'External Link', icon: 'link' }
  //     }
  //   ]
  // },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
