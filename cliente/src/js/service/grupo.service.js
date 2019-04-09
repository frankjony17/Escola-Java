(function () {
    'use strict';
    angular.module('angularStartApp').factory('GrupoService', service);

    function service($http) {
        const PORT = 'http://localhost:8080/';
        const URL = PORT + 'test/';

        function list(filter) {
            return $http.get(URL + 'grupo/list', {
                params: {
                    offset : filter.offset * filter.limit,
                    limit : filter.limit,
                }
            });
        }

        function save(grupo) {
            return $http.post(URL + 'grupo/save', grupo);
        }

        function edit(grupo) {
            return $http.post(URL + 'grupo/edit', grupo);
        }

        function remove(id) {
            return $http.delete(URL + 'grupo/remove/' + id);
        }

        return {
            list: list,
            save: save,
            edit: edit,
            remove: remove
        };
    }
})();
