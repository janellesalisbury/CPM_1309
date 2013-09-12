//
//  Stock.m
//  StockTrackr_iOSPortion
//
//  Created by Janelle Salisbury on 9/11/13.
//  Copyright (c) 2013 Janelle Salisbury. All rights reserved.
//

#import "Stock.h"

@implementation Stock
@synthesize _name, _stockID, _price;

-(void)setName:(NSString *)name{
    _name = name;
}
-(NSString*)getName{
    return _name;
}

-(void)setPrice:(NSString *)price{
    _price = price;
}
-(NSString*)getPrice{
    return _price;
}



@end
