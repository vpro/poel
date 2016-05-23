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
        this.$formSort = this.$form.find( '.sort-matches' );

        this.$alertOverlay = $( overlay );
        this.$alertOverlayButton = this.$alertOverlay.find( '.alert-overlay__close-button' );


        this.sortMatches();

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

        this.$formSort.on( 'click', function(){
            this.sortMatches();
        }.bind( this ) );

    },

    sortMatches: function(){

        // get all matches
        var matches = this.$matchesContainer.find( '.match' );

        // create an array with objects containing match + date
        var newMatches = matches.map( function( index, match, array ){

            var date = $( match ).find( 'input[type=datetime-local]' ).val()
            return {
                date: date,
                match: match
            }
        });

        // sort by date
        newMatches.sort( function( a, b ) {
            a = new Date( a.date );
            b = new Date( b.date );
            return a < b ? -1 : a > b ? 1 : 0;
        });

        // remove matches from the DOM
        this.$matchesContainer.empty();

        // inject matches in the new order
        newMatches.each( function( i, match ){
            this.$matchesContainer.append( match.match );
        }.bind( this ));

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