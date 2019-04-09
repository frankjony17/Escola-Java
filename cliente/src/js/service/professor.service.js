(function () {
    'use strict';
    angular.module('angularStartApp').factory('ProfessorService', service);

    function service($http) {
        const PORT = 'http://localhost:8080/';
        const URL = PORT + 'test/';

        function list(filter) {
            return $http.get(URL + 'professor/list', {
                params: {
                    offset : filter.offset,
                    limit : filter.limit,
                }
            });
        }

        // function save(professor) {
        //     return $http.post(URL + 'professor/save', professor);
        // }
        //
        // function edit(professor) {
        //     return $http.post(URL + 'professor/edit', professor);
        // }
        //
        // function remove(id) {
        //     return $http.delete(URL + 'professor/remove/' + id);
        // }

        return {
            list: list,
            // save: save,
            // edit: edit,
            // remove: remove
        };
    }
})();
