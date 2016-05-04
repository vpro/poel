import Stapes from 'stapes';

/**
 * @constructor
 * @param {string} id
 * @param {Array} contestants=[]
 * @param {Array} result=[]
 * @param {Array} gamble=[]
 * @param {../util/FormatDate} dueDate
 * @param {boolean} joker
 * @param {number} score
 */
var FormEntry = Stapes.subclass({

    constructor : function ( id, contestants, result, gamble, dueDate, joker, score ) {

        this.id = id;
        this.contestants = contestants;
        this.result = result;
        this.gamble = gamble;
        this.dueDate = dueDate;
        this.joker = joker;
        this.score = score;
    },

    toViewModel: function () {

        var viewModel =  {
            type: 'entry',
            id: this.id,
            result : this.result,
            gamble : this.gamble,
            dueDate : this.dueDate,
            joker : this.joker,
            score : this.score,
            definitive : false
        };

        this.contestants.forEach( function ( contestant, idx ) {
            viewModel[ 'contestant'+ idx ] = contestant;
        });

        if ( this.dueDate.getTime() < Date.now() ) {
            viewModel.definitive = true;
        }

        return viewModel;
    }
});


export default FormEntry;