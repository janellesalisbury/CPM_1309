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
    NSInteger *_stockID;
    NSString *_price;
    
}

@property(nonatomic, strong) NSString *_name;
@property(nonatomic, readwrite) NSInteger *_stockID;
@property(nonatomic, strong) NSString *_price;

-(void)setName: (NSString *)name;
-(NSString *)getName;

-(void)setId: (NSInteger *)stockID;
-(NSString *)getStockID;

-(void)setPrice: (NSString *)price;
-(NSString *)getPrice;

@end


