import { Component, OnInit } from '@angular/core';
import { RuleService } from '../services/rule.service';

@Component({
  selector: 'app-new-rule',
  templateUrl: './new-rule.component.html',
  styleUrls: ['./new-rule.component.css']
})
export class NewRuleComponent implements OnInit {
  silver = "";
  gold = "";
  platinum = "";

  constructor(private ruleService: RuleService) { }

  ngOnInit() {
  }

  newRule() {
    this.ruleService.newRule(this.silver, this.gold, this.platinum)
    .subscribe(
      (data: any) => {
        console.log(data);
      }
    )
  }

}
