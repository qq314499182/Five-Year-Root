export default [
  // user
  {
    path: '/user',
    component: '../layouts/UserLayout',
    routes: [
      { path: '/user', redirect: '/user/login' },
      { path: '/user/login', component: './User/FiveLogin' },
      { path: '/user/register', component: './User/FiveRegister' },
      { path: '/user/register-result', component: './User/FiveRegisterResult' },
    ],
  },
  // app
  {
    path: '/',
    component: '../layouts/BasicLayout',
    routes: [
      // 首页
      { path: '/',
        redirect: '/list/search/homepage'
      },
      // {
      //   path: '/dashboard',
      //   name: 'dashboard',
      //   icon: 'dashboard',
      //   routes: [
      //     {
      //       path: '/dashboard/analysis',
      //       name: 'analysis',
      //       component: './Dashboard/Analysis',
      //     },
      //     {
      //       path: '/dashboard/monitor',
      //       name: 'monitor',
      //       component: './Dashboard/Monitor',
      //     },
      //     {
      //       path: '/dashboard/workplace',
      //       name: 'workplace',
      //       component: './Dashboard/Workplace',
      //     },
      //   ],
      // },
      // forms
      {
        path: '/home',
        icon: 'home',
        name: 'home',
        component:'./List/Homepage'
      },
      {
        path: '/form/add-blog',
        icon: 'form',
        name: 'add.blog',
        component: './Forms/AddBlog',
        // routes: [
        //   {
        //     path: '/form/basic-form',
        //     name: 'basicform',
        //     component: './Forms/BasicForm',
        //   },
        //   {
        //     path: '/form/step-form',
        //     name: 'stepform',
        //     component: './Forms/StepForm',
        //     hideChildrenInMenu: true,
        //     routes: [
        //       {
        //         path: '/form/step-form',
        //         redirect: '/form/step-form/info',
        //       },
        //       {
        //         path: '/form/step-form/info',
        //         name: 'info',
        //         component: './Forms/StepForm/Step1',
        //       },
        //       {
        //         path: '/form/step-form/confirm',
        //         name: 'confirm',
        //         component: './Forms/StepForm/Step2',
        //       },
        //       {
        //         path: '/form/step-form/result',
        //         name: 'result',
        //         component: './Forms/StepForm/Step3',
        //       },
        //     ],
        //   },
        //   {
        //     path: '/form/advanced-form',
        //     name: 'advancedform',
        //     authority: ['admin'],
        //     component: './Forms/AdvancedForm',
        //   },
        //   {
        //     path: '/form/add-blog',
        //     name: '新增文章',
        //     component: './Forms/AddBlog',
        //   },
        // ],
      },
      // list
      {
        path: '/profile/BlogDetail',
        name: '文章详情',
        icon: 'profile',
        component: './Profile/BlogDetail',
        hideInMenu: true,
        // routes: [
        //   // profile
        //   {
        //     path: '/profile/basic',
        //     name: 'basic',
        //     component: './Profile/BasicProfile',
        //   },
        //   {
        //     path: '/profile/advanced',
        //     name: 'advanced',
        //     authority: ['admin'],
        //     component: './Profile/AdvancedProfile',
        //   },
        //   {
        //     path: '/profile/BlogDetail',
        //     name: '文章详情',
        //     component: './Profile/BlogDetail',
        //   },
        // ],
      },
      {
        name: 'result',
        icon: 'check-circle-o',
        path: '/result',
        hideInMenu: true,
        routes: [
          {
            path: '/result/success',
            name: 'success',
            component: './Result/Success',
          },
          { path: '/result/fail',
            name: 'fail',
            component: './Result/Error'
          },
        ],
      },
      // {
      //   name: 'exception',
      //   icon: 'warning',
      //   path: '/exception',
      //   routes: [
      //     // exception
      //     {
      //       path: '/exception/403',
      //       name: 'not-permission',
      //       component: './Exception/403',
      //     },
      //     {
      //       path: '/exception/404',
      //       name: 'not-find',
      //       component: './Exception/404',
      //     },
      //     {
      //       path: '/exception/500',
      //       name: 'server-error',
      //       component: './Exception/500',
      //     },
      //     {
      //       path: '/exception/trigger',
      //       name: 'trigger',
      //       hideInMenu: true,
      //       component: './Exception/TriggerException',
      //     },
      //   ],
      // },
      {
        name: 'account',
        icon: 'user',
        path: '/account',
        routes: [
          {
            path: '/account/center',
            name: 'center',
            component: './Account/Center/Center',
            routes: [
              {
                path: '/account/center',
                redirect: '/account/center/articles',
              },
              {
                path: '/account/center/articles',
                component: './Account/Center/Articles',
              },
              {
                path: '/account/center/applications',
                component: './Account/Center/Applications',
              },
              {
                path: '/account/center/projects',
                component: './Account/Center/Projects',
              },
            ],
          },
          {
            path: '/account/settings',
            name: 'settings',
            component: './Account/Settings/Info',
            routes: [
              {
                path: '/account/settings',
                redirect: '/account/settings/base',
              },
              {
                path: '/account/settings/base',
                component: './Account/Settings/BaseView',
              },
              {
                path: '/account/settings/security',
                component: './Account/Settings/SecurityView',
              },
              {
                path: '/account/settings/binding',
                component: './Account/Settings/BindingView',
              },
              {
                path: '/account/settings/notification',
                component: './Account/Settings/NotificationView',
              },
            ],
          },
        ],
      },
      // {
      //   path: 'test/test-list',
      //   component:'./Test/Test',
      //   name: "文档",
      //   icon: 'dashboard'
      // },
      // {
      //   component: '404',
      // },
    ],
  },
];
