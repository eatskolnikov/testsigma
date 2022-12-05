import {alias, custom, deserialize, optional, serializable} from "serializr";
import {CustomStep} from "./custom-step.model";
import {AddonTestDataFunction} from "./addon-test-data-function.model";
import {TestStepTestDataFunction} from "./test-step-test-data-function.model";
import {TestDataDetails} from "./step-details-test-data-map.model";
import {Optional} from "@angular/core";
import {Base} from "../shared/models/base.model";
import {AddonTestStepTestData} from "./addon-test-step-test-data.model";

export class LoopDataMap extends Base{
  @serializable(alias('testDataMap', (optional(custom(v=> {
    if(v){
      return v.serialize();
    } else {
      return v;
    }

  }, v => {
    if(v) {
      return new TestStepTestDataFunction().deserialize(v)
    } else{
      return v
    }
  } )))))
  public testDataFnData: TestStepTestDataFunction;
  @serializable(alias('kibbutzPluginTDFEntityList',(optional(custom(v=> {
    if(v){
      return v.serialize();
    } else {
      return v;
    }

  }, v => {
    if(v) {
      return new AddonTestStepTestData().deserialize(v)
    } else{
      return v
    }
  } )))))
  public kibbutzFnData: AddonTestStepTestData;

  deserialize(input: any): this {
    return Object.assign(this, deserialize(LoopDataMap, input));
  }
}
