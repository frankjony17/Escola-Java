(function () {
    'use strict';
    angular.module('angularStartApp').factory('AppService', service);

    function service($mdToast) {

        const TIPO = {
            ERRO: "error-toast",
            SUCESSO: "success-toast"
        };
        const MESSAGE = {
            DADOS_ENVIADOS_SUCESSO: "Dados enviados com sucesso",
            CAMPOS_OBRIGATORIOS: "Dados obrigatórios não preenchidos",
            UNICO_CAMPO_YA_EXISTE: "Um campo com este valor já existe"
        };

        function _showMessage(msg, tipo) {
            $mdToast.show($mdToast.simple().content(msg).theme(tipo).position('top right').hideDelay(3000));
        }

        /**
         * Mostrar mensagem de erro.
         * @param msg
         */
        function toastError(msg) {
            _showMessage(msg, TIPO.ERRO);
        }

        /**
         * Mostrar mensagem de dados enviados com sucesso.
         * @param msg
         */
        function toastSuccess(msg) {
            _showMessage(msg, TIPO.SUCESSO);
        }

        /**
         * Mostrar mensagem de dados obrigatórios não preenchidos.
         */
        function toastCamposObrigatorios() {
            _showMessage(MESSAGE.CAMPOS_OBRIGATORIOS, TIPO.ERRO);
        }

        /**
         * Sucesso, retornar dados ou mostrar mensagem de sucesso.
         * @param response
         * @returns {*}
         */
        function successCallback(response) {
            if (response.data.result) {
                return {
                    result: response.data.result,
                    totalElements: response.data.totalElements,
                }
            } else {
                _showMessage(MESSAGE.DADOS_ENVIADOS_SUCESSO, TIPO.SUCESSO);
            }
        }

        /**
         * Error, amostra mensagem de error ou valor único.
         * @param response
         */
        function errorCallback(response) {
            if (response.data.errorMessage.includes('UNIQUE-VALUES')){
                _showMessage(MESSAGE.UNICO_CAMPO_YA_EXISTE, TIPO.ERRO);
            } else {
                _showMessage(response.data.errorMessage, TIPO.ERRO);
            }
        }

        /**
         * Limpa todos os campos no formulário.
         * Mostrar mensagem de dados enviados com sucesso.
         * Atualizar tabela.
         * @param response
         * @param model
         * @param form
         * @param list_function
         */
        function successSaveCallback(model, form, list_function) {
            cleanModelForm(model, form); // Limpar atributos do objeto.
            list_function(); // Atualizar tabela.
            _showMessage(MESSAGE.DADOS_ENVIADOS_SUCESSO, TIPO.SUCESSO); // Mostrar mensagem de dados enviados com sucesso.
        }

        /**
         * Colocar uma linha vermelha nos campos dos formulários.
         * @param form.
         */
        function validityFields(form) {
            angular.forEach(form, function (element, name) {
                if (!name.startsWith('$')) {
                    element.$setTouched();
                }
            });
            toastCamposObrigatorios(); // Mostrar mensagem de dados obrigatórios não preenchidos.
        }

        /**
         * Limpar atributos do model no form.
         * @param model
         * @param form
         */
        function cleanModelForm(model, form) {
            let keys = Object.keys(model); // Obter os atributos do objeto.
            for (let i = 0; i < keys.length; i++) {
                model[keys[i]] = ""; // Limpar atributos do objeto.
            }
            form.$setUntouched(); // remova a linha vermelha.
        }

        return {
            toastError: toastError,
            toastSuccess: toastSuccess,
            toastCamposObrigatorios: toastCamposObrigatorios,
            successCallback: successCallback,
            successSaveCallback: successSaveCallback,
            errorCallback: errorCallback,
            validityFields: validityFields,
            cleanModelForm: cleanModelForm
        };
    }
})();
