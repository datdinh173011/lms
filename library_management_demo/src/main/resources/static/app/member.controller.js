var memberApp = angular.module('member',[])

memberApp.controller('MemberController', function($http, $window){
    var vm = this;

    vm.member;
    vm.members = [];

    vm.add = function(){
        $http.post('/api/member/create',{
            type: vm.member.type,
            firstName: vm.member.firstName,
            middleName: vm.member.middleName,
            lastName:vm.member.lastName,
            gender:vm.member.gender,
            dateOfBirth:vm.member.dateOfBirth,
            contact:vm.member.contact,
            email:vm.member.email
        }).then(function(response){
            $window.location.reload();
        });
    };

    vm.list = function(){
        $http.get('/rest/member/list').then(function(response){
            vm.members = response.data
        });
    };

    vm.update = function(){
        $http.put('/api/member/edit',{
            id:vm.member.id,
            type: vm.member.type,
            firstName: vm.member.firstName,
            middleName: vm.member.middleName,
            lastName:vm.member.lastName,
            gender:vm.member.gender,
            dateOfBirth:vm.member.dateOfBirth,
            contact:vm.member.contact,
            email:vm.member.email
        }).then(function(response){

        });
    };

    vm.delete = function(){
        $http.delete('api/member/delete',{
            params: {memberId: member.id}
        }).then(function(response){
            $window.location.reload();
        });
    };

    vm.isValidateBirthDate = function isValidateBirthDate() {
        var dateStr = $('#dateOfBirth').val();
        var timestamp = Date.parse(dateStr)
        return !isNaN(timestamp)
    }


    vm.save = function(){
        if (vm.member.id){
            vm.update();
        }else{
            vm.add();
        }
    };

});
