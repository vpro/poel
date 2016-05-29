import Stapes from 'stapes';
import $ from 'jquery';

var UserFormController = Stapes.subclass({

    constructor : function (  $overlay ) {

        this.$alertOverlay = $overlay;
        this.$alertOverlayButton = this.$alertOverlay.find( '.alert-overlay__close-button' );

        this.bindHandlers();

    },

    bindHandlers: function () {

        this.$alertOverlayButton.on( 'click', function () {

            this.$alertOverlay.addClass( 'hidden' );

        }.bind( this ) );

    }

});

export default UserFormController;