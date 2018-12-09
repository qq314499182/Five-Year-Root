import React,{Component} from 'react';
import {Card} from 'antd';

export default class TestFrom extends Component{
  constructor(props) {
    super(props);
    this.state = {
      cardList: [
        {
          id:1,
          setup:'hello',
          punchline:'world'
        },
        {
          id:1,
          setup:'hello',
          punchline:'world'
        }
      ]
    }
  }

  render() {
    return (
      <div>
        {
          this.state.cardList.map(card => {
            return (
              <Card key={card.id}>
                <div>Q: {card.setup}</div>
                <div>
                  <strong>A: {card.punchline}</strong>
                </div>
              </Card>
            );
          })
        }
      </div>
    );
  }
}
