import Stapes from 'stapes';
import $ from 'jquery';

import HandlebarsRuntime from 'handlebars-runtime';
import messageTemplate from '../views/message.hbs!';

var MessageController = Stapes.subclass({

    constructor : function ( $form, $overlay ) {

        this.$form = $form;
        this.$messageContainer = this.$form.find( '.messages' );
        this.$formSubmit = this.$form.find( 'button[type=submit]' );
        this.$formReset = this.$form.find( 'button[type=reset]' );
        this.$formAdd = this.$form.find( '.add-message' );

        this.$alertOverlay = $overlay;
        this.$alertOverlayButton = this.$alertOverlay.find( '.alert-overlay__close-button' );

        this.initialFormState = this.$form.serialize();

        this.bindHandlers();

    },

    addMessage: function () {

        this.$messageContainer.append(

            messageTemplate({
                index: $( '.message-admin' ).length
            })
        );
    },

    bindHandlers: function () {

        this.$formReset.on( 'click', function () {
            setTimeout( function () {
                this.checkFormChanges();
            }.bind( this ), 1 );
        }.bind( this ) );


        this.$formAdd.on( 'click', function () {
            this.addMessage();
        }.bind( this ) );


        this.$alertOverlayButton.on( 'click', function () {
            this.$alertOverlay.addClass( 'hidden' );
        }.bind( this ) );


        this.$form.on( 'click', '.delete-message', function ( e ) {

            var c = confirm( 'Bericht verwijderen?' );
            if ( c == true ) {
                var message = $( e.currentTarget ).parent();
                this.deleteMessage( message );
                this.resetIds();
            }

        }.bind( this ) );

    },

    deleteMessage: function ( message ) {

        message.remove();

    },

    resetIds: function () {

        this.$form.find( '.message-admin').each( function ( i ) {

            $( this ).find( 'input[name*="messages"]' ).each( function ( j ) {

                var idx = /\[([\d]+)\]/ig.exec( this.name );
                if ( idx && idx.length > 1 ) {

                    this.name = this.name.replace( idx[ 1 ], i );
                }
            });
        } );

    }

});

export default MessageController;