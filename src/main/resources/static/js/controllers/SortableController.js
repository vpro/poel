import Stapes from 'stapes';
import $ from 'jquery';

var SortableController = Stapes.subclass({

    /**
     *
     * @param $formSorter Button that triggers the sorting
     * @param $entityContainer
     * @param entitySelector
     */
    constructor : function ( $formSorter, $entityContainer, entitySelector ) {

        this.$entityContainer = $entityContainer;
        this.$formSorter = $formSorter;
        this.entitySelector = entitySelector;

        this.sortEntities();

        this.bindHandlers();

    },

    bindHandlers: function () {

        this.$formSorter.on( 'click', function () {
            this.sortEntities();
        }.bind( this ) );

    },

    sortEntities: function () {

        var entities = this.$entityContainer.find( this.entitySelector );
        var entityObjects = entities.map( function ( index, entity, array ) {

            var date = $( entity ).find( 'input[type=datetime-local]' ).val();

            return {
                date: date,
                entity: entity
            };
        });

        entityObjects.sort( function ( a, b ) {
            a = new Date( a.date );
            b = new Date( b.date );

            return a < b ? -1 : a > b ? 1 : 0;
        });

        // remove original entities from the DOM
        this.$entityContainer.empty();

        // inject the ordered entityObjects
        entityObjects.each( function ( i, entity ) {
            this.$entityContainer.append( entity.entity );
        }.bind( this ) );

    }
});

export default SortableController;