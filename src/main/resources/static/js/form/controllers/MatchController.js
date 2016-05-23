/*

    TODO:
        - add match
        - remove match
        - save all matches

*/

import Stapes from 'stapes';
import $ from 'jquery';

import HandlebarsRuntime from 'handlebars-runtime';
import matchTemplate from '../views/match.hbs!';

var MatchController = Stapes.subclass({

    /**
     * @param {HTMLElement} form
     */
    constructor : function ( form, overlay ) {

        this.$form = $( form );
        this.$matchesContainer = this.$form.find( '.matches' );
        this.$formSubmit = this.$form.find( 'button[type=submit]' );
        this.$formReset = this.$form.find( 'button[type=reset]' );
        this.$formAdd = this.$form.find( '.add-match' );

//        this.$matchPredictions = this.$form.find( '.match-prediction' );
//        this.$predictionInputs = this.$form.find( 'input.prediction' );

        this.$alertOverlay = $( overlay );
        this.$alertOverlayButton = this.$alertOverlay.find( '.alert-overlay__close-button' );

        this.initialFormState = this.$form.serialize();

        this.bindHandlers();

    },

    bindHandlers: function () {

        this.$formReset.on( 'click', function () {
            setTimeout( function () {
                this.checkFormChanges();
            }.bind( this ), 1 );
        }.bind( this ) );


        this.$formAdd.on( 'click', function () {
            this.addMatch();
        }.bind( this ) );


        this.$alertOverlayButton.on( 'click', function () {
            this.$alertOverlay.addClass( 'hidden' );
        }.bind( this ) );


        this.$form.on( 'click', '.delete-match', function( e ){

            var match = $( e.currentTarget ).parent();

            this.deleteMatch( match );

        }.bind( this ) );

    },

    addMatch: function () {

        this.$matchesContainer.append(

            matchTemplate({
                index: $( '.match' ).length
            })
        )
    },

    deleteMatch: function( match ){

        match.remove();

    },

    /**
     *
     * Check if the form has changed since page load
     *
     */

    checkFormChanges: function () {
        if( this.hasFormChangedSincePageLoad() ) {
            this.enableSubmit();
        } else {
            this.disableSubmit();
        }
    },

    enableSubmit: function () {
        this.$formSubmit.prop( 'disabled', false );
    },

    disableSubmit: function () {
        this.$formSubmit.prop( 'disabled', true );
    },

    hasFormChangedSincePageLoad: function () {

        return this.$form.serialize() !== this.initialFormState;
    }
});

export default MatchController;