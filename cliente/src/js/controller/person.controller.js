(function () {
    'use strict';
    angular.module('angularStartApp').controller('PersonController', ['PersonService', 'AppService', '$mdDialog', controller]);

    function controller(PersonService, AppService, $mdDialog) {
        const me = this;

        me.model = {
            id: "", firstName: "", lastName: ""
        };

        me.pessoaList = [];

        me.table = {
            limit: 7,
            limitsPage: [7, 15, 25],
            page: 1,
            total: 0
        };

        function init() {
            me.list();
        }

        me.limpar = function () {
            AppService.cleanModelForm(me.model, me.pessoaForm);
        };

        me.list = function () {
            PersonService.list({
                offset: me.table.page - 1,
                limit: me.table.limit
            }).then(
                function (response) {
                    let data = AppService.successCallback(response);
                    me.pessoaList = data.result;
                    me.totalElements = data.totalElements;
                },
                AppService.errorCallback
            );
        };

        me.loadForm = function (pessoa) {
            me.model = pessoa; // carregar o formulário.
        };

        me.isValid = function () {
            if (me.pessoaForm.$valid) {
                if (me.model.id != "") { // então edite.
                    edit();
                } else { // então crie um novo.
                    save();
                }
            } else {
                AppService.validityFields(me.pessoaForm);
            }
        };

        me.confirmRemove = function (ev, id) {
            let confirm = $mdDialog.confirm()
                .title('Remover?')
                .textContent('Deseja realmente excluir este registro.')
                .targetEvent(ev)
                .ok('Sim')
                .cancel('Não');

            $mdDialog.show(confirm).then(function() {
                remove(id);
            });
        };

        function save () {
            PersonService.save(me.model).then(function () {
                AppService.successSaveCallback(me.model, me.pessoaForm, me.list);
            }, AppService.errorCallback);
        }

        function edit () {
            PersonService.edit(me.model).then(function () {
                AppService.successSaveCallback(me.model, me.pessoaForm, me.list);
            }, AppService.errorCallback);
        }

        function remove (id) {
            PersonService.remove(id).then(function (response) {
                console.log(response);
               AppService.successCallback(response);
               me.list();
            }, AppService.errorCallback);
        }
        // Init function;
        init();
    }
})();
