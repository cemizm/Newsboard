'use strict';

angular.module('nwbdoku.manual', ['ui.router'])
    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.when('', '/frontend/web');
        $urlRouterProvider.when('/frontend', '/frontend/web');
        $urlRouterProvider.when('/admin', '/admin/dashboard');

        $stateProvider.state('manual', {
            url: '',
            templateUrl: 'views/index.html'
        }).state('manual.frontend', {
            url: '/frontend',
            templateUrl: 'views/frontend/index.html'
        }).state('manual.frontend.web', {
            url: '/web',
            templateUrl: 'views/frontend/web.html'
        }).state('manual.frontend.public', {
            url: '/public',
            templateUrl: 'views/frontend/public.html'
        }).state('manual.admin', {
            url: '/admin',
            templateUrl: 'views/admin/index.html'
        }).state('manual.admin.dashboard', {
            url: '/dashboard',
            templateUrl: 'views/admin/dashboard.html'
        }).state('manual.admin.crawler', {
            url: '/crawler',
            templateUrl: 'views/admin/crawler.html'
        }).state('manual.admin.analyzer', {
            url: '/analyzer',
            templateUrl: 'views/admin/analyzer.html'
        }).state('manual.admin.group', {
            url: '/group',
            templateUrl: 'views/admin/group.html'
        }).state('manual.admin.view', {
            url: '/view',
            templateUrl: 'views/admin/view.html'
        });
    }]);