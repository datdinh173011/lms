(function(){
    'use strict';

    angular
        .module('app')
        .controller('BookController', CategoryController)

    BookingsController.$inject = ['$http','$window'];

    function CategoryController($http){
        var vm=this;

        vm.book;
        vm.books = [];
        vm.getAll = getAll;
        init();

        function init(){
            getAll();
        }

        function getAll(){
            var url = "/rest/book/list";
            var bookingsPromise = $http.get(url);
            bookingsPromise.then(function(response){
                vm.books = response.data;
                console.log("test" + vm.books);
                debugger
            });
        };

        function postAdd(){
            $http.post('/api/book/save',{
                title: vm.book.title,
                tag: vm.book.tag,
                authors: vm.book.authors,
                publisher: vm.book.publisher
            }).then(function(response){
                $window.location.reload();
            });
        };


    }
    })();