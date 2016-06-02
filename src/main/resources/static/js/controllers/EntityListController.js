/**
 * Controller for editing a list of simple entities
 */

import Stapes from 'stapes';
import $ from 'jquery';

var EntityListController = Stapes.subclass({

    /**
     *
     * @param $form
     * @param $overlay
     * @param template
     * @param modelAttribute - the entity list variable name parsed by the back end controller
     */
    constructor : function ( $form, $overlay, template, modelAttribute ) {

        this.$form = $form;
        this.template = template;
        this.modelAttribute = modelAttribute;
        this.$entityContainer = this.$form.find( '.entities' );
        this.$formSubmit = this.$form.find( 'button[type=submit]' );
        this.$formReset = this.$form.find( 'button[type=reset]' );
        this.$formAdd = this.$form.find( '.add-entity' );

        this.$alertOverlay = $overlay;
        this.$alertOverlayButton = this.$alertOverlay.find( '.alert-overlay__close-button' );

        this.initialFormState = this.$form.serialize();

        this.bindHandlers();

    },

    addEntity: function () {

        this.$entityContainer.append(

            this.template({
                index: $( '.entity-admin' ).length
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
            this.addEntity();
        }.bind( this ) );


        this.$alertOverlayButton.on( 'click', function () {
            this.$alertOverlay.addClass( 'hidden' );
        }.bind( this ) );


        this.$form.on( 'click', '.delete-entity', function ( e ) {

            var c = confirm( 'Object verwijderen?' );
            if ( c == true ) {
                var entity = $( e.currentTarget ).parents('.entity-admin');
                this.deleteEntity( entity );
                this.resetIds();
            }

        }.bind( this ) );

    },

    deleteEntity: function ( entity ) {

        entity.remove();

    },

    resetIds: function () {

        var modelAttribute = this.modelAttribute;

        this.$form.find( '.entity-admin').each( function ( i ) {

            $( this ).find( '[name*="'+ modelAttribute +'"]' ).each( function ( j ) {

                var idx = /\[([\d]+)\]/ig.exec( this.name );
                if ( idx && idx.length > 1 ) {

                    this.name = this.name.replace( idx[ 1 ], i );
                }
            });
        } );

    }

});

export default EntityListController;