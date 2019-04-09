(function () {
    'use strict';
    angular.module('angularStartApp').factory('PersonService', service);

    function service($http) {
        const PORT = 'http://localhost:8080/';
        const URL = PORT + 'test/';

        function list(filter) {
            return $http.get(URL + 'pessoa/list', {
                params: {
                    offset : filter.offset * filter.limit,
                    limit : filter.limit,
                }
            });
        }

        function save(pessoa) {
            return $http.post(URL + 'pessoa/save', pessoa);
        }

        function edit(pessoa) {
            return $http.post(URL + 'pessoa/edit', pessoa);
        }

        function remove(id) {
            return $http.delete(URL + 'pessoa/remove/' + id);
        }

        return {
            list: list,
            save: save,
            edit: edit,
            remove: remove
        };
    }
})();
