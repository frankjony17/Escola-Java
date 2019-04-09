(function () {
    'use strict';
    angular.module('angularStartApp').config(function ($stateProvider) {
        $stateProvider
            .state('back', {
                url: '/',
                ncyBreadcrumb: {
                    label: 'home'
                }
            })
            .state('person', {
                url: '/listPerson',
                templateUrl: 'views/person.html',
                controller: 'PersonController as personCtrl',
                ncyBreadcrumb: {
                    label: 'Consultar Pessoas',
                    parent: 'home'
                }
            })
            .state('grupo', {
                url: '/listGrupo',
                templateUrl: 'views/grupo.html',
                controller: 'GrupoController as grupoCtrl',
                ncyBreadcrumb: {
                    label: 'Consultar Grupos',
                    parent: 'home'
                }
            })
            .state('professor', {
                url: '/listProfessor',
                templateUrl: 'views/professor.html',
                controller: 'ProfessorController as professorCtrl',
                ncyBreadcrumb: {
                    label: 'Consultar Professor',
                    parent: 'home'
                }
            })
    });
})();
