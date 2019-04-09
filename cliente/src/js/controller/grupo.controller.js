(function () {
    'use strict';
    angular.module('angularStartApp').controller('GrupoController', ['GrupoService', 'AppService', '$mdDialog', controller]);

    function controller(GrupoService, AppService, $mdDialog) {
        const me = this;

        me.model = {
            id: "", nome: "", location: ""
        };

        me.grupoList = [];

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
            AppService.cleanModelForm(me.model, me.grupoForm);
        };

        me.list = function () {
            GrupoService.list({
                offset: me.table.page -1,
                limit: me.table.limit
            }).then(
                function (response) {
                    let data = AppService.successCallback(response);
                    me.grupoList = data.result;
                    me.totalElements = data.totalElements;
                },
                AppService.errorCallback
            );
        };

        me.loadForm = function (grupo) {
            me.model = grupo; // carregar o formulário.
        };

        me.isValid = function () {
            if (me.grupoForm.$valid) {
                if (me.model.id != "") { // então edite.
                    edit();
                } else { // então crie um novo.
                    save();
                }
            } else {
                AppService.validityFields(me.grupoForm);
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
            GrupoService.save(me.model).then(function () {
                AppService.successSaveCallback(me.model, me.grupoForm, me.list);
            }, AppService.errorCallback);
        }

        function edit () {
            GrupoService.edit(me.model).then(function () {
                AppService.successSaveCallback(me.model, me.grupoForm, me.list);
            }, AppService.errorCallback);
        }

        function remove (id) {
            GrupoService.remove(id).then(function (response) {
                console.log(response);
                AppService.successCallback(response);
                me.list();
            }, AppService.errorCallback);
        }
        // Init function;
        init();
    }
})();
