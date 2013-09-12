//
//  Stock.h
//  StockTrackr_iOSPortion
//
//  Created by Janelle Salisbury on 9/11/13.
//  Copyright (c) 2013 Janelle Salisbury. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Stock : NSObject
{
    NSString *_name;
    NSString *_stockID;
    NSString *_price;
    
}

@property(nonatomic, strong) NSString *_name;
@property(nonatomic, strong) NSString *_stockID;
@property(nonatomic, strong) NSString *_price;

-(void)setName: (NSString *)name;
-(NSString *)getName;

-(void)setId: (NSString *)stockID;
-(NSString *)getStockID;

-(void)setPrice: (NSString *)price;
-(NSString *)getPrice;

@end


