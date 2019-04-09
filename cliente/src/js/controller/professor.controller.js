(function () {
    'use strict';
    angular.module('angularStartApp').controller('ProfessorController', ['ProfessorService', 'AppService', '$mdDialog', '$scope', controller]);

    function controller(ProfessorService, AppService, $mdDialog, $scope) {
        const me = this;

        $scope.oneAtATime = true;

        me.model = {
            id: "", firstName: "", lastName: "", specialty:  "", experience: "", dataEntry: "", numeroEstudantes: 0
        };

        me.professorList = [];

        me.table = {
            limit: 7,
            limitsPage: [7, 15, 25],
            page: 1,
            total: 0
        };

        function init() {
            me.list();
        }

        // me.limpar = function () {
        //     AppService.cleanModelForm(me.model, me.professorForm);
        // };

        me.list = function () {
            ProfessorService.list({
                offset: me.table.page - 1,
                limit: me.table.limit
            }).then(
                function (response) {
                    let data = AppService.successCallback(response);
                    me.professorList = data.result;
                    me.totalElements = data.totalElements;
                },
                AppService.errorCallback
            );
        };

        // me.loadForm = function (professor) {
        //     me.model = professor; // carregar o formulário.
        // };
        //
        // me.isValid = function () {
        //     if (me.professorForm.$valid) {
        //         if (me.model.id != "") { // então edite.
        //             edit();
        //         } else { // então crie um novo.
        //             save();
        //         }
        //     } else {
        //         AppService.validityFields(me.professorForm);
        //     }
        // };

        // me.confirmRemove = function (ev, id) {
        //     let confirm = $mdDialog.confirm()
        //         .title('Remover?')
        //         .textContent('Deseja realmente excluir este registro.')
        //         .targetEvent(ev)
        //         .ok('Sim')
        //         .cancel('Não');
        //
        //     $mdDialog.show(confirm).then(function() {
        //         remove(id);
        //     });
        // };
        //
        // function save () {
        //     ProfessorService.save(me.model).then(function () {
        //         AppService.successSaveCallback(me.model, me.professorForm, me.list);
        //     }, AppService.errorCallback);
        // }
        //
        // function edit () {
        //     ProfessorService.edit(me.model).then(function () {
        //         AppService.successSaveCallback(me.model, me.professorForm, me.list);
        //     }, AppService.errorCallback);
        // }
        //
        // function remove (id) {
        //     ProfessorService.remove(id).then(function (response) {
        //         console.log(response);
        //        AppService.successCallback(response);
        //        me.list();
        //     }, AppService.errorCallback);
        // }
        // Init function;
        init();
    }
})();
