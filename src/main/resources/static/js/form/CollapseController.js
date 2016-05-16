import Stapes from 'stapes';
import $ from 'jquery';

var CollapseController = Stapes.subclass( {

    constructor : function ( sections ) {
        this.$sections = $( sections );
        this.bindViewHandlers();
    },

    bindViewHandlers: function () {

        //console.log( this.$sections );

    }

});

export default CollapseController;