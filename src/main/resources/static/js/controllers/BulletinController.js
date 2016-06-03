import Stapes from 'stapes';
import $ from 'jquery';

import HandlebarsRuntime from 'handlebars-runtime';
import bulletinTemplate from '../views/bulletin.hbs!';

var BulletinController = Stapes.subclass({

    constructor : function ( $form, $overlay ) {

        this.$form = $form;
        this.$bulletinContainer = this.$form.find( '.bulletins' );
        this.$formSubmit = this.$form.find( 'button[type=submit]' );
        this.$formReset = this.$form.find( 'button[type=reset]' );
        this.$formAdd = this.$form.find( '.add-bulletin' );

        this.$alertOverlay = $overlay;
        this.$alertOverlayButton = this.$alertOverlay.find( '.alert-overlay__close-button' );

        this.initialFormState = this.$form.serialize();

        this.bindHandlers();

    },

    addBulletin: function () {

        this.$bulletinContainer.append(

            bulletinTemplate({
                index: $( '.bulletin-admin' ).length
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
            this.addBulletin();
        }.bind( this ) );


        this.$alertOverlayButton.on( 'click', function () {
            this.$alertOverlay.addClass( 'hidden' );
        }.bind( this ) );


        this.$form.on( 'click', '.delete-bulletin', function ( e ) {

            var c = confirm( 'Bericht verwijderen?' );
            if ( c == true ) {
                var bulletin = $( e.currentTarget ).parents('.bulletin-admin');
                this.deleteBulletin( bulletin );
                this.resetIds();
            }

        }.bind( this ) );

    },

    deleteBulletin: function ( bulletin ) {

        bulletin.remove();

    },

    resetIds: function () {

        this.$form.find( '.bulletin-admin').each( function ( i ) {

            $( this ).find( '[name*="bulletins"]' ).each( function ( j ) {

                var idx = /\[([\d]+)\]/ig.exec( this.name );
                if ( idx && idx.length > 1 ) {

                    this.name = this.name.replace( idx[ 1 ], i );
                }
            });
        } );

    }

});

export default BulletinController;