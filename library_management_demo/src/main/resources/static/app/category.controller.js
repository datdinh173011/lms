(function(){
    'use strict';

    angular
        .module('app')
        .controller('CategoryController', CategoryController)

    BookingsController.$inject = ['$http','$window'];

    function CategoryController($http){
        var vm=this;

        vm.category;
        vm.categories = [];
        vm.getAll = getAll;
        init();

        function init(){
            getAll();
        }

        function getAll(){
            var url = "/api/category/show";
            var bookingsPromise = $http.get(url);
            bookingsPromise.then(function(response){
                vm.categories = response.data;
                console.log("test" + vm.categories);
                debugger
            });
        };

        function postAdd(){
            $http.post('/api/category/create',{
                name: vm.category.name,
                shortName: vm.category.shortName,
                notes: vm.category.notes
            }).then(function(response){
                $window.location.reload();
            });
        };

        function updateData(){
            $http.get('/api/category/edit' + {
                id: vm.category.id,
                name: vm.category.name,
                shortName: vm.category.shortName,
                notes: vm.category.notes
            }).then(function(response){

            });
        };

    }
    })();